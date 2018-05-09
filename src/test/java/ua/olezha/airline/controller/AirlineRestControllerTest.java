package ua.olezha.airline.controller;

import org.junit.Before;
import org.junit.Test;
import ua.olezha.airline.service.AircraftService;

import static org.mockito.Mockito.*;

public class AirlineRestControllerTest {

    private AirlineRestController airlineRestController;

    @Before
    public void setUp() {
        airlineRestController = new AirlineRestController(mock(AircraftService.class));
    }

    @Test
    public void totalCapacityOfAllTheAircraftInTheAirlineTest() {
        airlineRestController.totalCapacityOfAllTheAircraftInTheAirline();
    }

    @Test
    public void carryingCapacityOfAllTheAircraftInTheAirlineTest() {
        airlineRestController.carryingCapacityOfAllTheAircraftInTheAirline();
    }

    @Test
    public void aircraftSortedByFlightRangeTest() {
        airlineRestController.aircraftSortedByFlightRange();
    }

    @Test
    public void airplanesCorrespondingToAGivenRangeOfFuelConsumptionParametersTest() {
        airlineRestController.airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(1, 1);
    }

    @Test
    public void aircraftCorrespondingToSpecifiedPassengerCapacityAndFlightRangeTest() {
        airlineRestController.aircraftCorrespondingToSpecifiedPassengerCapacityAndFlightRange(1, 1);
    }
}
