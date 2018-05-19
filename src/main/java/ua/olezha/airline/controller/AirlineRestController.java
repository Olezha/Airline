package ua.olezha.airline.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.olezha.airline.model.aircraft.Aircraft;
import ua.olezha.airline.service.AircraftService;

import java.util.List;

@RestController
public class AirlineRestController {

    private final AircraftService aircraftService;

    public AirlineRestController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping("/api/aircraft-list")
    public List<Aircraft> getAircraftList() {
        return aircraftService.allAircraftInTheAirline();
    }

    @GetMapping("/total-passenger-capacity")
    public int totalCapacityOfAllTheAircraftInTheAirline() {
        return aircraftService.totalCapacityOfAllTheAircraftInTheAirline();
    }

    @GetMapping("/total-cargo-capacity")
    public int carryingCapacityOfAllTheAircraftInTheAirline() {
        return aircraftService.carryingCapacityOfAllTheAircraftInTheAirline();
    }

    @GetMapping("/aircraft-sorted-by-flight-range")
    public List<Aircraft> aircraftSortedByFlightRange() {
        return aircraftService.sortTheAircraftByFlightRangeFromSmallerToLarger();
    }

    @GetMapping("/aircraft-in-fuel-range")
    public List<Aircraft> airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(
            @RequestParam("from") int from, @RequestParam("to") int to) {
        return aircraftService.findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters(from, to);
    }

    @GetMapping("/aircraft-corresponding-to")
    public List<Aircraft> aircraftCorrespondingToSpecifiedPassengerCapacityAndFlightRange(
            @RequestParam("seating-capacity") int seatingCapacity, @RequestParam("flight-range") int flightRange) {
        return aircraftService.search(seatingCapacity, -1, flightRange, -1);
    }
}
