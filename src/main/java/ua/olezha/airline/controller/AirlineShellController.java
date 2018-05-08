package ua.olezha.airline.controller;

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

    public AirlineShellController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
        System.out.println("Airline (to display available shell commands type help)");
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Add aircraft", key = "add", prefix = "-")
    private void addAircraft(
            @ShellOption(help = "Name")
                    String name,
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
        aircraft.setName(name);
        aircraft.setSeatingCapacity(seatingCapacity);
        aircraft.setCarryingCapacityKg(carryingCapacityKg);
        aircraft.setFlightRangeKm(flightRangeKm);
        aircraft.setFuelConsumptionLitersPerHour(fuelConsumptionLitersPerHour);
        aircraftService.addAircraft(aircraft);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Delete aircraft", key = "del", prefix = "-")
    private void deleteAircraft(@ShellOption(help = "ID") int id) {
        aircraftService.delete(id);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Add aircraft", key = "update", prefix = "-")
    private void updateAircraft(
            @ShellOption(help = "ID")
                    int id,
            @ShellOption(help = "Name", defaultValue = "-1")
                    String name,
            @ShellOption(help = "Seating capacity", defaultValue = "-1")
                    int seatingCapacity,
            @ShellOption(help = "Carrying capacity (kg)", defaultValue = "-1")
                    int carryingCapacityKg,
            @ShellOption(help = "Flight range (km)", defaultValue = "-1")
                    int flightRangeKm,
            @ShellOption(help = "Fuel consumption (liters per hour)", defaultValue = "-1")
                    int fuelConsumptionLitersPerHour) {
        aircraftService.update(id, name, seatingCapacity, carryingCapacityKg, flightRangeKm, fuelConsumptionLitersPerHour);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Show all aircraft", key = "show", prefix = "-")
    private Object showAllAircraft(boolean raw) {
        List<Aircraft> aircraftList = aircraftService.allAircraftInTheAirline();
        if (raw)
            return aircraftList;
        return aircraftListToASCIITable(aircraftList);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Total capacity of all the aircraft in the airline", key = "tc", prefix = "-")
    private Object totalCapacity(boolean raw) {
        int passengerCapacity = aircraftService.totalCapacityOfAllTheAircraftInTheAirline();
        if (raw)
            return passengerCapacity;
        return "Passenger capacity: " + passengerCapacity;
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Carrying capacity of all the aircraft in the airline", key = "cc", prefix = "-")
    private Object carryingCapacity(boolean raw) {
        int cargoCapacity = aircraftService.carryingCapacityOfAllTheAircraftInTheAirline();
        if (raw)
            return cargoCapacity;
        return "Cargo capacity: "+ cargoCapacity;
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "List of aircraft of the company sorted by flight range", key = "sort", prefix = "-")
    private Object aircraftSortedByFlightRange(boolean desc, boolean raw) {
        List<Aircraft> aircraftList = aircraftService.sortTheAircraftByFlightRangeFromSmallerToLarger();
        if (desc)
            Collections.reverse(aircraftList);
        if (raw)
            return aircraftList;
        return aircraftListToASCIITable(aircraftList);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Airplanes corresponding to a given range of fuel consumption parameters", key = "fuel", prefix = "-")
    private Object airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(
            @ShellOption(help = "From (liters per hour)")
                    int fromLitersPerHour,
            @ShellOption(help = "To (liters per hour)")
                    int toLitersPerHour,
            boolean raw) {
        List<Aircraft> aircraftList =
                aircraftService.findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters(
                        fromLitersPerHour, toLitersPerHour);
        if (raw)
            return aircraftList;
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
        Class<?>[] classes = new Class[]{Commuterliner.class, Helicopter.class, WideBodyAirliner.class};
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
    private Object search(
            @ShellOption(help = "Seating capacity", defaultValue = "-1")
                    int seatingCapacity,
            @ShellOption(help = "Carrying capacity (kg)", defaultValue = "-1")
                    int carryingCapacityKg,
            @ShellOption(help = "Flight range (km)", defaultValue = "-1")
                    int flightRangeKm,
            @ShellOption(help = "Fuel consumption (liters per hour)", defaultValue = "-1")
                    int fuelConsumptionLitersPerHour,
            boolean raw) {
        List<Aircraft> aircraftList =
                aircraftService.search(seatingCapacity, carryingCapacityKg, flightRangeKm, fuelConsumptionLitersPerHour);
        if (raw)
            return aircraftList;
        return aircraftListToASCIITable(aircraftList);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Range search", prefix = "-")
    private Object rangeSearch(
            @ShellOption(help = "From seating capacity", defaultValue = "-1")
                    int fromSeatingCapacity,
            @ShellOption(help = "To seating capacity", defaultValue = "-1")
                    int toSeatingCapacity,
            @ShellOption(help = "From carrying capacity (kg)", defaultValue = "-1")
                    int formCarryingCapacityKg,
            @ShellOption(help = "To carrying capacity (kg)", defaultValue = "-1")
                    int toCarryingCapacityKg,
            @ShellOption(help = "From flight range (km)", defaultValue = "-1")
                    int fromFlightRangeKm,
            @ShellOption(help = "To flight range (km)", defaultValue = "-1")
                    int toFlightRangeKm,
            @ShellOption(help = "From fuel consumption (liters per hour)", defaultValue = "-1")
                    int fromFuelConsumptionLitersPerHour,
            @ShellOption(help = "To fuel consumption (liters per hour)", defaultValue = "-1")
                    int toFuelConsumptionLitersPerHour,
            boolean raw) {
        List<Aircraft> aircraftList =
                aircraftService.search(fromSeatingCapacity, formCarryingCapacityKg,
                        fromFlightRangeKm, fromFuelConsumptionLitersPerHour,
                        toSeatingCapacity, toCarryingCapacityKg,
                        toFlightRangeKm, toFuelConsumptionLitersPerHour);
        if (raw)
            return aircraftList;
        return aircraftListToASCIITable(aircraftList);
    }

    private String aircraftListToASCIITable(List<Aircraft> aircraftList) {
        LinkedHashMap<String, Object> headers = new LinkedHashMap<>();
        headers.put("id", "ID");
        headers.put("name", "Name");
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
@SuppressWarnings("unused")
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
