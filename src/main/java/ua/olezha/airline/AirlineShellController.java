package ua.olezha.airline;

import com.thoughtworks.xstream.XStream;
import org.jline.utils.AttributedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.*;
import org.springframework.stereotype.Component;
import ua.olezha.airline.model.aircraft.*;
import ua.olezha.airline.service.AircraftService;

import java.io.File;
import java.util.*;

@ShellComponent
public class AirlineShellController {

    private final AircraftService aircraftService;

    @Autowired
    public AirlineShellController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
        System.out.println("Airline (to display available commands type help)");
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Add aircraft", key = "add", prefix = "-")
    private void addAircraft(
            @ShellOption(help = "Type [WIDE_BODY_AIRLINER|COMMUTERLINER|HELICOPTER]")
            AircraftType aircraftType,
            @ShellOption(help = "Seating capacity", defaultValue = "0")
            int seatingCapacity,
            @ShellOption(help = "Carrying capacity (kg)", defaultValue = "0")
            int carryingCapacityKg,
            @ShellOption(help = "Flight range (km)", defaultValue = "0")
            int flightRangeKm,
            @ShellOption(help = "Fuel consumption (liters per hour)", defaultValue = "0")
            int fuelConsumptionLitersPerHour) {
        if (aircraftType == null) {
            System.out.println("Unknown aircraft type. Allowable values " + Arrays.asList(AircraftType.values()));
            return;
        }
        Aircraft aircraft = aircraftService.aircraftFactory(aircraftType);
        aircraft.setSeatingCapacity(seatingCapacity);
        aircraft.setCarryingCapacityKg(carryingCapacityKg);
        aircraft.setFlightRangeKm(flightRangeKm);
        aircraft.setFuelConsumptionLitersPerHour(fuelConsumptionLitersPerHour);
        aircraftService.addAircraft(aircraft);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Show all aircraft", key = "show")
    private String showAllAircraft() {
        List<Aircraft> aircraftList = aircraftService.allAircraftInTheAirline();
        return aircraftListToASCIITable(aircraftList);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Total capacity of all the aircraft in the airline", key = "tc")
    private int totalCapacity() {
        return aircraftService.totalCapacityOfAllTheAircraftInTheAirline();
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Carrying capacity of all the aircraft in the airline", key = "cc")
    private int carryingCapacity() {
        return aircraftService.carryingCapacityOfAllTheAircraftInTheAirline();
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "List of aircraft of the company sorted by flight range", key = "sort", prefix = "-")
    private String aircraftSortedByFlightRange(boolean desc) {
        List<Aircraft> aircraftList = aircraftService.sortTheAircraftByFlightRangeFromSmallerToLarger();
        if (desc)
            Collections.reverse(aircraftList);
        return aircraftListToASCIITable(aircraftList);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Airplanes corresponding to a given range of fuel consumption parameters", key = "fuel", prefix = "-")
    private String airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(
            @ShellOption(help = "From (liters per hour)")
            int fromLitersPerHour,
            @ShellOption(help = "To (liters per hour)")
            int toLitersPerHour) {
        List<Aircraft> aircraftList =
                aircraftService.findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters(
                        fromLitersPerHour, toLitersPerHour);
        return aircraftListToASCIITable(aircraftList);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Delete", prefix = "-")
    private void delete(boolean all) {
        if (!all)
            System.out.println("Unsupported operation");
        else
            aircraftService.deleteAll();
    }

    @SuppressWarnings({"unused", "unchecked"})
    @ShellMethod("Simulate objects")
    private void mock() {
        XStream xstream = new XStream();
        Class<?>[] classes = new Class[] {Commuterliner.class, Helicopter.class, WideBodyAirliner.class};
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        xstream.alias("commuterliner", Commuterliner.class);
        xstream.alias("helicopter", Helicopter.class);
        xstream.alias("wideBodyAirliner", WideBodyAirliner.class);
        File aircraftListXmlFile = new File(Aircraft.class
                .getResource("/aircraftList.xml")
                .getFile());
        for (Aircraft aircraft : (List<Aircraft>)
                xstream.fromXML(aircraftListXmlFile))
            aircraftService.addAircraft(aircraft);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Search", prefix = "-")
    private String search(
                          @ShellOption(help = "Seating capacity", defaultValue = "-1")
                          int seatingCapacity,
                          @ShellOption(help = "Carrying capacity (kg)", defaultValue = "-1")
                          int carryingCapacityKg,
                          @ShellOption(help = "Flight range (km)", defaultValue = "-1")
                          int flightRangeKm,
                          @ShellOption(help = "Fuel consumption (liters per hour)", defaultValue = "-1")
                          int fuelConsumptionLitersPerHour) {
        List<Aircraft> aircraftList =
                aircraftService.search(seatingCapacity, carryingCapacityKg, flightRangeKm, fuelConsumptionLitersPerHour);
        return aircraftListToASCIITable(aircraftList);
    }

    private String aircraftListToASCIITable(List<Aircraft> aircraftList) {
        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("id", "ID");
        headers.put("type", "Type");
        headers.put("seatingCapacity", "Seating capacity");
        headers.put("carryingCapacityKg", "Carrying capacity, Kg");
        headers.put("flightRangeKm", "Flight range, Km");
        headers.put("fuelConsumptionLitersPerHour", "Fuel consumption, L/h");
        TableBuilder tableBuilder = new TableBuilder(
                new BeanListTableModel<>(aircraftList, headers));
        Table table = tableBuilder
                .addFullBorder(BorderStyle.fancy_light_double_dash)
                .addHeaderBorder(BorderStyle.fancy_light)
                .addOutlineBorder(BorderStyle.fancy_light)
                .build();
        return table.render(180);
    }
}

@Component
class PromptProvider implements org.springframework.shell.jline.PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return AttributedString.fromAnsi("airline>");
    }
}

@Component
class AircraftTypeEnumConverter implements Converter<String, AircraftType> {

    @Override
    public AircraftType convert(String s) {
        try {
            return AircraftType.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
