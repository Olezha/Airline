package ua.olezha.airline;

import asg.cliche.Command;
import asg.cliche.Param;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.olezha.airline.model.aircraft.Aircraft;
import ua.olezha.airline.service.AircraftService;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class AirlineController {

    private final AircraftService aircraftService;

    @Autowired
    public AirlineController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @Command
    public void addAircraft(
            @Param(name = "Type [WideBodyAirliner|Commuterliner|Helicopter]")
                    String type,
            @Param(name = "Seating capacity")
                    int seatingCapacity,
            @Param(name = "Carrying capacity (kg)")
                    int carryingCapacityKg,
            @Param(name = "Flight range (km)")
                    int flightRangeKm,
            @Param(name = "Fuel consumption (liters per hour)")
                    int fuelConsumptionLitersPerHour) {
            try {
                Aircraft aircraft = aircraftService.aircraftFactory(type);
                aircraft.setSeatingCapacity(seatingCapacity);
                aircraft.setCarryingCapacityKg(carryingCapacityKg);
                aircraft.setFlightRangeKm(flightRangeKm);
                aircraft.setFuelConsumptionLitersPerHour(fuelConsumptionLitersPerHour);
                aircraftService.addAircraft(aircraft);
            } catch (IllegalArgumentException e) {
                log.info(e.getLocalizedMessage());
            }
    }

    @Command
    public List<Aircraft> showAllAircraft() {
        return aircraftService.allAircraftInTheAirline();
    }

    // Total capacity of all the aircraft in the airline
    @Command
    public int totalCapacity() {
        return aircraftService.totalCapacityOfAllTheAircraftInTheAirline();
    }

    // Carrying capacity of all the aircraft in the airline
    @Command
    public int carryingCapacity() {
        return aircraftService.carryingCapacityOfAllTheAircraftInTheAirline();
    }

    // List of aircraft of the company sorted by flight range
    @Command
    public List<Aircraft> aircraftSortedByFlightRange() {
        return aircraftService.sortTheAircraftByFlightRangeFromSmallerToLarger();
    }

    @Command
    public List<Aircraft> aircraftSortedByFlightRange(@Param(name = "Direction [ASC|DESC]") String direction) {
        List<Aircraft> aircraftList = aircraftService.sortTheAircraftByFlightRangeFromSmallerToLarger();
        if ("DESC".equalsIgnoreCase(direction))
            Collections.reverse(aircraftList);
        return aircraftList;
    }

    @Command
    public List<Aircraft> airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(
            @Param(name = "From (liters per hour)")
                    int fromLitersPerHour,
            @Param(name = "To (liters per hour)")
                    int toLitersPerHour) {
        return aircraftService.findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters(
                fromLitersPerHour, toLitersPerHour);
    }

    @Command
    public void mock() throws InstantiationException, IllegalAccessException {
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
