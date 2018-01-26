package ua.olezha.airline;

import asg.cliche.Command;
import asg.cliche.Param;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.olezha.airline.model.Aircraft;
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
            @Param(name = "type [WideBodyAirliner|Commuterliner|Helicopter]")
                    String type,
            @Param(name = "seating capacity")
                    int seatingCapacity,
            @Param(name = "carrying capacity (kg)")
                    int carryingCapacityKg,
            @Param(name = "flight range (km)")
                    int flightRangeKm,
            @Param(name = "fuel consumption (liters per hour)")
                    int fuelConsumptionLitersPerHour)
            throws IllegalAccessException, InstantiationException {
        try {
            Class<?> aircraftClass = Class.forName("ua.olezha.airline.model." + type);
            Aircraft aircraft = (Aircraft) aircraftClass.newInstance();
            aircraft.setSeatingCapacity(seatingCapacity);
            aircraft.setCarryingCapacityKg(carryingCapacityKg);
            aircraft.setFlightRangeKm(flightRangeKm);
            aircraft.setFuelConsumptionLitersPerHour(fuelConsumptionLitersPerHour);
            aircraftService.addAircraft(aircraft);
        } catch (ClassNotFoundException | ClassCastException e) {
            log.info("{} is an unknown type of aircraft", type);
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
        return aircraftService.sortTheAircraftsByFlightRangeFromSmallerToLarger();
    }

    @Command
    public List<Aircraft> aircraftSortedByFlightRange(@Param(name = "direction [ASC|DESC]") String direction) {
        List<Aircraft> aircraftList = aircraftService.sortTheAircraftsByFlightRangeFromSmallerToLarger();
        if ("DESC".equalsIgnoreCase(direction))
            Collections.reverse(aircraftList);
        return aircraftList;
    }

    @Command
    public List<Aircraft> airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(
            @Param(name = "from (liters per hour)")
                    int fromLitersPerHour,
            @Param(name = "to (liters per hour)")
                    int toLitersPerHour) {
        return aircraftService.findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParametersLitersPerHour(
                fromLitersPerHour, toLitersPerHour);
    }
}
