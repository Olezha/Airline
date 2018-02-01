package ua.olezha.airline;

import org.jline.utils.AttributedString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Component;
import ua.olezha.airline.model.aircraft.Aircraft;
import ua.olezha.airline.model.aircraft.AircraftType;
import ua.olezha.airline.service.AircraftService;

import java.util.Collections;
import java.util.List;

@ShellComponent()
public class AirlineShellController {

    private final AircraftService aircraftService;

    @Autowired
    public AirlineShellController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
        System.out.println("Airline (to display available commands type help)");
    }

    @ShellMethod(value = "Add aircraft", key = "add", prefix="-")
    private void addAircraft(
            // TODO: ConversionFailedException
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
            Aircraft aircraft = aircraftService.aircraftFactory(aircraftType);
            aircraft.setSeatingCapacity(seatingCapacity);
            aircraft.setCarryingCapacityKg(carryingCapacityKg);
            aircraft.setFlightRangeKm(flightRangeKm);
            aircraft.setFuelConsumptionLitersPerHour(fuelConsumptionLitersPerHour);
            aircraftService.addAircraft(aircraft);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Show all aircraft", key = "show")
    private List<Aircraft> showAllAircraft() {
        return aircraftService.allAircraftInTheAirline();
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
    @ShellMethod(value = "List of aircraft of the company sorted by flight range", key = "sort", prefix="-")
    private List<Aircraft> aircraftSortedByFlightRange(boolean desc) {
        List<Aircraft> aircraftList = aircraftService.sortTheAircraftByFlightRangeFromSmallerToLarger();
        if (desc)
            Collections.reverse(aircraftList);
        return aircraftList;
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Airplanes corresponding to a given range of fuel consumption parameters", key = "fuel", prefix="-")
    private List<Aircraft> airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(
            @ShellOption(help = "From (liters per hour)")
            int fromLitersPerHour,
            @ShellOption(help = "To (liters per hour)")
            int toLitersPerHour) {
        return aircraftService.findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters(
                fromLitersPerHour, toLitersPerHour);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Delete", prefix="-")
    private void delete(boolean all) {
        if (!all)
            System.out.println("Unsupported operation");
        else
            aircraftService.deleteAll();
    }

    @SuppressWarnings("unused")
    @ShellMethod("Simulate objects")
    private void mock() {
        addAircraft(AircraftType.COMMUTERLINER, 10, 2000, 10000, 150);
        addAircraft(AircraftType.HELICOPTER, 18, 800, 3050, 350);
        addAircraft(AircraftType.WIDE_BODY_AIRLINER, 200, 18000, 14300, 555);
        addAircraft(AircraftType.COMMUTERLINER, 150, 5550, 1000, 990);
        addAircraft(AircraftType.HELICOPTER, 4, 2110, 2300, 120);
        addAircraft(AircraftType.WIDE_BODY_AIRLINER, 777, 400, 19500, 1150);
        addAircraft(AircraftType.COMMUTERLINER, 101, 2014, 1675, 350);
        addAircraft(AircraftType.HELICOPTER, 9, 900, 1900, 120);
        addAircraft(AircraftType.WIDE_BODY_AIRLINER, 1020, 12000, 10100, 1600);
        addAircraft(AircraftType.WIDE_BODY_AIRLINER, 555, 14500, 9900, 2500);
        addAircraft(AircraftType.COMMUTERLINER, 19, 4500, 5675, 1001);
        addAircraft(AircraftType.HELICOPTER, 24, 2600, 900, 630);
        addAircraft(AircraftType.WIDE_BODY_AIRLINER, 8, 8950, 12500, 1340);
        addAircraft(AircraftType.COMMUTERLINER, 189, 1600, 19000, 2250);
    }

    @SuppressWarnings("unused")
    @ShellMethod(value = "Search", prefix="-")
    private List<Aircraft> search(// TODO: Valid *
            @ShellOption(help = "Seating capacity", defaultValue = "-1")
                    int seatingCapacity,
            @ShellOption(help = "Carrying capacity (kg)", defaultValue = "-1")
                    int carryingCapacityKg,
            @ShellOption(help = "Flight range (km)", defaultValue = "-1")
                    int flightRangeKm,
            @ShellOption(help = "Fuel consumption (liters per hour)", defaultValue = "-1")
                    int fuelConsumptionLitersPerHour) {
        return aircraftService.search(seatingCapacity, carryingCapacityKg, flightRangeKm, fuelConsumptionLitersPerHour);
    }
}

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class PromptProvider implements org.springframework.shell.jline.PromptProvider {

    @Override
    public AttributedString getPrompt() {
        return AttributedString.fromAnsi("airline>");
    }
}
