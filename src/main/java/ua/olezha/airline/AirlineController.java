package ua.olezha.airline;

import asg.cliche.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.olezha.airline.model.Aircraft;
import ua.olezha.airline.service.AircraftService;

import java.util.List;

@Component
public class AirlineController {

    private final AircraftService aircraftService;

    @Autowired
    public AirlineController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    // Total capacity of all the aircraft in the airline
    @Command
    public int totalCapacity() {
        return 0;
    }

    // Carrying capacity of all the aircraft in the airline
    @Command
    public int carryingCapacity() {
        return 0;
    }

    // TODO: ASC|DESC
    // List of aircraft of the company sorted by flight range
    @Command
    public List<Aircraft> aircraftSortedByFlightRange() {
        return null;
    }

    @Command
    public List<Aircraft> airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(
            int from, int to) {
        return null;
    }
}
