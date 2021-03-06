package ua.olezha.airline.controller;

import com.thoughtworks.xstream.XStream;
import org.jline.utils.AttributedString;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.shell.table.BeanListTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.Table;
import org.springframework.shell.table.TableBuilder;
import org.springframework.stereotype.Component;
import ua.olezha.airline.model.aircraft.*;
import ua.olezha.airline.service.AircraftService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@ShellComponent
public class AirlineShellController {

    private final AircraftService aircraftService;
    private final ResourceLoader resourceLoader;

    public AirlineShellController(AircraftService aircraftService,
                                  ResourceLoader resourceLoader) {
        this.aircraftService = aircraftService;
        this.resourceLoader = resourceLoader;
        System.out.println("Airline (to display available shell commands type help)");
    }

    @ShellMethod(value = "Add aircraft", key = "add", prefix = "-")
    protected void addAircraft(
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

    @ShellMethod(value = "Delete aircraft", key = "del", prefix = "-")
    protected void deleteAircraft(@ShellOption(help = "ID") int id) {
        aircraftService.delete(id);
    }

    @ShellMethod(value = "Add aircraft", key = "update", prefix = "-")
    protected void updateAircraft(
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

    @ShellMethod(value = "Show all aircraft", key = "show", prefix = "-")
    protected Object showAllAircraft(boolean raw) {
        List<Aircraft> aircraftList = aircraftService.allAircraftInTheAirline();
        if (raw)
            return aircraftList;
        return aircraftListToASCIITable(aircraftList);
    }

    @ShellMethod(value = "Total capacity of all the aircraft in the airline", key = "tc", prefix = "-")
    protected Object totalCapacity(boolean raw) {
        int passengerCapacity = aircraftService.totalCapacityOfAllTheAircraftInTheAirline();
        if (raw)
            return passengerCapacity;
        return "Passenger capacity: " + passengerCapacity;
    }

    @ShellMethod(value = "Carrying capacity of all the aircraft in the airline", key = "cc", prefix = "-")
    protected Object carryingCapacity(boolean raw) {
        int cargoCapacity = aircraftService.carryingCapacityOfAllTheAircraftInTheAirline();
        if (raw)
            return cargoCapacity;
        return "Cargo capacity: " + cargoCapacity;
    }

    @ShellMethod(value = "List of aircraft of the company sorted by flight range", key = "sort", prefix = "-")
    protected Object aircraftSortedByFlightRange(boolean desc, boolean raw) {
        List<Aircraft> aircraftList = aircraftService.sortTheAircraftByFlightRangeFromSmallerToLarger();
        if (desc)
            Collections.reverse(aircraftList);
        if (raw)
            return aircraftList;
        return aircraftListToASCIITable(aircraftList);
    }

    @ShellMethod(value = "Airplanes corresponding to a given range of fuel consumption parameters", key = "fuel", prefix = "-")
    protected Object airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(
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

    @ShellMethod(value = "Delete", prefix = "-")
    protected void delete(boolean all) {
        if (!all)
            System.out.println("Unsupported operation");
        else
            aircraftService.deleteAll();
    }

    @ShellMethod("Simulate objects")
    protected void mock() throws IOException {
        XStream xstream = new XStream();
        Class<?>[] classes = new Class[]{Commuterliner.class, Helicopter.class, WideBodyAirliner.class};
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);
        xstream.alias("commuterliner", Commuterliner.class);
        xstream.alias("helicopter", Helicopter.class);
        xstream.alias("wideBodyAirliner", WideBodyAirliner.class);
        InputStream aircraftListXmlInputStream = resourceLoader.getResource("classpath:aircraftList.xml").getInputStream();
        for (Aircraft aircraft : (List<Aircraft>)
                xstream.fromXML(aircraftListXmlInputStream))
            aircraftService.addAircraft(aircraft);
    }

    @ShellMethod(value = "Search", prefix = "-")
    protected Object search(
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

    protected String aircraftListToASCIITable(List<Aircraft> aircraftList) {
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
