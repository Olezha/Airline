package ua.olezha.airline.controller;

import org.junit.Before;
import org.junit.Test;
import ua.olezha.airline.service.AircraftService;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AirlineRestControllerTest {

    private AirlineRestController airlineRestController;

    @Before
    public void setUp() {
        airlineRestController = new AirlineRestController(mock(AircraftService.class));
    }

    @Test
    public void totalCapacityOfAllTheAircraftInTheAirlineTest() {
        assertNotNull(airlineRestController.totalCapacityOfAllTheAircraftInTheAirline());
    }

    @Test
    public void carryingCapacityOfAllTheAircraftInTheAirlineTest() {
        assertNotNull(airlineRestController.carryingCapacityOfAllTheAircraftInTheAirline());
    }

    @Test
    public void aircraftSortedByFlightRangeTest() {
        assertNotNull(airlineRestController.aircraftSortedByFlightRange());
    }

    @Test
    public void airplanesCorrespondingToAGivenRangeOfFuelConsumptionParametersTest() {
        assertNotNull(airlineRestController.airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(1, 1));
    }

    @Test
    public void aircraftCorrespondingToSpecifiedPassengerCapacityAndFlightRangeTest() {
        assertNotNull(airlineRestController.aircraftCorrespondingToSpecifiedPassengerCapacityAndFlightRange(1, 1));
    }
}
