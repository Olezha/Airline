package ua.olezha.airline.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ua.olezha.airline.model.aircraft.Aircraft;
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
    public void addAircraft(
            @ShellOption(help = "Type [WideBodyAirliner|Commuterliner|Helicopter]")
            String type,
            @ShellOption(help = "Seating capacity", defaultValue = "0")
            int seatingCapacity,
            @ShellOption(help = "Carrying capacity (kg)", defaultValue = "0")
            int carryingCapacityKg,
            @ShellOption(help = "Flight range (km)", defaultValue = "0")
            int flightRangeKm,
            @ShellOption(help = "Fuel consumption (liters per hour)", defaultValue = "0")
            int fuelConsumptionLitersPerHour) {
        try {
            Aircraft aircraft = aircraftService.aircraftFactory(type);
            aircraft.setSeatingCapacity(seatingCapacity);
            aircraft.setCarryingCapacityKg(carryingCapacityKg);
            aircraft.setFlightRangeKm(flightRangeKm);
            aircraft.setFuelConsumptionLitersPerHour(fuelConsumptionLitersPerHour);
            aircraftService.addAircraft(aircraft);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @ShellMethod(value = "Show all aircraft", key = "show")
    public List<Aircraft> showAllAircraft() {
        return aircraftService.allAircraftInTheAirline();
    }

    @ShellMethod(value = "Total capacity of all the aircraft in the airline", key = "tc")
    public int totalCapacity() {
        return aircraftService.totalCapacityOfAllTheAircraftInTheAirline();
    }

    @ShellMethod(value = "Carrying capacity of all the aircraft in the airline", key = "cc")
    public int carryingCapacity() {
        return aircraftService.carryingCapacityOfAllTheAircraftInTheAirline();
    }

    @ShellMethod(value = "List of aircraft of the company sorted by flight range", key = "sort", prefix="-")
    public List<Aircraft> aircraftSortedByFlightRange(boolean desc) {
        List<Aircraft> aircraftList = aircraftService.sortTheAircraftByFlightRangeFromSmallerToLarger();
        if (desc)
            Collections.reverse(aircraftList);
        return aircraftList;
    }

    @ShellMethod(value = "Airplanes corresponding to a given range of fuel consumption parameters", key = "fuel", prefix="-")
    public List<Aircraft> airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(
            @ShellOption(help = "From (liters per hour)")
            int fromLitersPerHour,
            @ShellOption(help = "To (liters per hour)")
            int toLitersPerHour) {
        return aircraftService.findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters(
                fromLitersPerHour, toLitersPerHour);
    }

    @ShellMethod("Simulate objects")
    public void mock() {
        addAircraft("Commuterliner", 10, 2000, 10000, 150);
        addAircraft("Helicopter", 18, 800, 3050, 350);
        addAircraft("WideBodyAirliner", 200, 18000, 14300, 555);
        addAircraft("Commuterliner", 150, 5550, 1000, 990);
        addAircraft("Helicopter", 4, 2110, 2300, 120);
        addAircraft("WideBodyAirliner", 777, 400, 19500, 1150);
        addAircraft("Commuterliner", 101, 2014, 1675, 350);
        addAircraft("Helicopter", 9, 900, 1900, 120);
        addAircraft("WideBodyAirliner", 1020, 12000, 10100, 1600);
        addAircraft("WideBodyAirliner", 555, 14500, 9900, 2500);
        addAircraft("Commuterliner", 19, 4500, 5675, 1001);
        addAircraft("Helicopter", 24, 2600, 900, 630);
        addAircraft("WideBodyAirliner", 8, 8950, 12500, 1340);
        addAircraft("Commuterliner", 189, 1600, 19000, 2250);
    }
}
