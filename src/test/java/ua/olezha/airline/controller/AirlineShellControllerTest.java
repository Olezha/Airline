package ua.olezha.airline.controller;

import org.junit.Before;
import org.junit.Test;
import ua.olezha.airline.model.aircraft.AircraftType;
import ua.olezha.airline.model.aircraft.Commuterliner;
import ua.olezha.airline.model.aircraft.Helicopter;
import ua.olezha.airline.model.aircraft.WideBodyAirliner;
import ua.olezha.airline.service.AircraftService;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AirlineShellControllerTest {

    private AirlineShellController airlineShellController;

    @Before
    public void setUp() {
        AircraftService aircraftServiceMock = mock(AircraftService.class);
        when(aircraftServiceMock.aircraftFactory(AircraftType.WIDE_BODY_AIRLINER)).thenReturn(new WideBodyAirliner());
        when(aircraftServiceMock.aircraftFactory(AircraftType.COMMUTERLINER)).thenReturn(new Commuterliner());
        when(aircraftServiceMock.aircraftFactory(AircraftType.HELICOPTER)).thenReturn(new Helicopter());
        airlineShellController = new AirlineShellController(aircraftServiceMock);
    }

    @Test
    public void addAircraftTest() {
        airlineShellController.addAircraft("", AircraftType.WIDE_BODY_AIRLINER, 1, 1, 1, 1);
    }

    @Test
    public void deleteAircraftTest() {
        airlineShellController.deleteAircraft(1);
    }

    @Test
    public void updateAircraftTest() {
        airlineShellController.updateAircraft(1, "", 1, 1, 1, 1);
    }

    @Test
    public void showAllAircraftTest() {
        assertNotNull(airlineShellController.showAllAircraft(false));
        assertNotNull(airlineShellController.showAllAircraft(true));
    }

    @Test
    public void totalCapacityTest() {
        assertNotNull(airlineShellController.totalCapacity(false));
        assertNotNull(airlineShellController.totalCapacity(true));
    }

    @Test
    public void carryingCapacityTest() {
        assertNotNull(airlineShellController.carryingCapacity(false));
        assertNotNull(airlineShellController.carryingCapacity(true));
    }

    @Test
    public void aircraftSortedByFlightRangeTest() {
        assertNotNull(airlineShellController.aircraftSortedByFlightRange(false, false));
        assertNotNull(airlineShellController.aircraftSortedByFlightRange(true, false));
        assertNotNull(airlineShellController.aircraftSortedByFlightRange(false, true));
        assertNotNull(airlineShellController.aircraftSortedByFlightRange(true, true));
    }

    @Test
    public void airplanesCorrespondingToAGivenRangeOfFuelConsumptionParametersTest() {
        assertNotNull(airlineShellController.airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(1, 1, false));
        assertNotNull(airlineShellController.airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(1, 1, true));
    }

    @Test
    public void deleteTest() {
        airlineShellController.delete(false);
        airlineShellController.delete(true);
    }

    @Test
    public void mockTest() {
        airlineShellController.mock();
    }

    @Test
    public void searchTest() {
        assertNotNull(airlineShellController.search(1, 1, 1, 1, false));
        assertNotNull(airlineShellController.search(1, 1, 1, 1, true));
    }

    @Test
    public void rangeSearchTest() {
        assertNotNull(airlineShellController.rangeSearch(1, 1, 1, 1, 1, 1, 1, 1, false));
        assertNotNull(airlineShellController.rangeSearch(1, 1, 1, 1, 1, 1, 1, 1, true));
    }

    @Test
    public void aircraftListToASCIITableTest() {
        assertNotNull(airlineShellController.aircraftListToASCIITable(new ArrayList<>()));
    }
}
