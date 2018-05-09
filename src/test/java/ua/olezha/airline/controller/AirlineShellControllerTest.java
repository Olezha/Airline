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
        airlineShellController.showAllAircraft(false);
        airlineShellController.showAllAircraft(true);
    }

    @Test
    public void totalCapacityTest() {
        airlineShellController.totalCapacity(false);
        airlineShellController.totalCapacity(true);
    }

    @Test
    public void carryingCapacityTest() {
        airlineShellController.carryingCapacity(false);
        airlineShellController.carryingCapacity(true);
    }

    @Test
    public void aircraftSortedByFlightRangeTest() {
        airlineShellController.aircraftSortedByFlightRange(false, false);
        airlineShellController.aircraftSortedByFlightRange(true, false);
        airlineShellController.aircraftSortedByFlightRange(false, true);
        airlineShellController.aircraftSortedByFlightRange(true, true);
    }

    @Test
    public void airplanesCorrespondingToAGivenRangeOfFuelConsumptionParametersTest() {
        airlineShellController.airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(1, 1, false);
        airlineShellController.airplanesCorrespondingToAGivenRangeOfFuelConsumptionParameters(1, 1, true);
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
        airlineShellController.search(1, 1, 1, 1, false);
        airlineShellController.search(1, 1, 1, 1, true);
    }

    @Test
    public void rangeSearchTest() {
        airlineShellController.rangeSearch(1, 1, 1, 1, 1, 1, 1, 1, false);
        airlineShellController.rangeSearch(1, 1, 1, 1, 1, 1, 1, 1, true);
    }

    @Test
    public void aircraftListToASCIITableTest() {
        airlineShellController.aircraftListToASCIITable(new ArrayList<>());
    }
}
