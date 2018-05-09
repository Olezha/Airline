package ua.olezha.airline.service;

import org.junit.Before;
import org.junit.Test;
import ua.olezha.airline.model.aircraft.*;
import ua.olezha.airline.model.company.Company;
import ua.olezha.airline.repository.AircraftRepository;
import ua.olezha.airline.repository.CompanyRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AircraftServiceImplTests {

    private Company company;
    private AircraftService aircraftService;
    private AircraftRepository aircraftRepositoryMock;

    @Before
    public void setUp() {
        company = new Company();
        CompanyRepository companyRepositoryMock =
                mock(CompanyRepository.class);
        when(companyRepositoryMock.getOne(1L)).thenReturn(company);
        aircraftRepositoryMock = mock(AircraftRepository.class);
        aircraftService = new AircraftServiceImpl(
                aircraftRepositoryMock, companyRepositoryMock);
    }

    @Test
    public void addAircraftSuccessfully() {
        aircraftService.addAircraft(new Commuterliner());
        assertThat(company.getAircraftList().size(), is(1));
    }

    @Test
    public void getAllAircraftInTheAirlineSuccessfully() {
        aircraftService.addAircraft(new Helicopter());
        aircraftService.addAircraft(new WideBodyAirliner());
        assertThat(aircraftService.allAircraftInTheAirline().size(), is(2));
        aircraftService.addAircraft(new Commuterliner());
        aircraftService.addAircraft(new Commuterliner());
        assertThat(aircraftService.allAircraftInTheAirline().size(), is(4));
    }

    @Test
    public void testTotalCapacityOfAllTheAircraftInTheAirline() {
        int seatingCapacity = 0;
        Helicopter helicopter = new Helicopter();
        helicopter.setSeatingCapacity(18);
        seatingCapacity += helicopter.getSeatingCapacity();
        aircraftService.addAircraft(helicopter);
        Commuterliner commuterliner = new Commuterliner();
        commuterliner.setSeatingCapacity(80);
        seatingCapacity += commuterliner.getSeatingCapacity();
        aircraftService.addAircraft(commuterliner);
        WideBodyAirliner wideBodyAirliner = new WideBodyAirliner();
        wideBodyAirliner.setSeatingCapacity(450);
        seatingCapacity += wideBodyAirliner.getSeatingCapacity();
        aircraftService.addAircraft(wideBodyAirliner);
        Helicopter helicopter2 = new Helicopter();
        helicopter2.setSeatingCapacity(8);
        seatingCapacity += helicopter2.getSeatingCapacity();
        aircraftService.addAircraft(helicopter2);

        when(aircraftRepositoryMock.totalCapacityOfAllTheAircraftInTheAirline())
                .thenReturn(company.getAircraftList()
                        .parallelStream()
                        .mapToInt(Aircraft::getSeatingCapacity).sum()
                );

        assertThat(aircraftService.totalCapacityOfAllTheAircraftInTheAirline(),
                is(seatingCapacity));
    }

    @Test
    public void testCarryingCapacityOfAllTheAircraftInTheAirline() {
        int carryingCapacity = 0;
        Helicopter helicopter = new Helicopter();
        helicopter.setCarryingCapacityKg(5000);
        carryingCapacity += helicopter.getCarryingCapacityKg();
        aircraftService.addAircraft(helicopter);
        WideBodyAirliner wideBodyAirliner = new WideBodyAirliner();
        wideBodyAirliner.setCarryingCapacityKg(180000);
        carryingCapacity += wideBodyAirliner.getCarryingCapacityKg();
        aircraftService.addAircraft(wideBodyAirliner);

        when(aircraftRepositoryMock.carryingCapacityOfAllTheAircraftInTheAirline())
                .thenReturn(company.getAircraftList()
                        .parallelStream()
                        .mapToInt(Aircraft::getCarryingCapacityKg).sum()
                );

        assertThat(aircraftService.carryingCapacityOfAllTheAircraftInTheAirline(),
                is(carryingCapacity));
    }

    @Test
    public void testSortTheAircraftByFlightRangeFromSmallerToLarger() {
        Helicopter helicopter = new Helicopter();
        helicopter.setFlightRangeKm(800);
        aircraftService.addAircraft(helicopter);
        WideBodyAirliner wideBodyAirliner = new WideBodyAirliner();
        wideBodyAirliner.setFlightRangeKm(11000);
        aircraftService.addAircraft(wideBodyAirliner);

        List<Aircraft> aircraftListSortedByFlightRangeFromSmallerToLarger =
                aircraftService.sortTheAircraftByFlightRangeFromSmallerToLarger();
        assertTrue(
                aircraftListSortedByFlightRangeFromSmallerToLarger.get(0).getFlightRangeKm()
                        < aircraftListSortedByFlightRangeFromSmallerToLarger.get(1).getFlightRangeKm());
    }

    @Test
    public void findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParametersLitersPerHourSuccessfully() {
        Helicopter helicopter = new Helicopter();
        helicopter.setFuelConsumptionLitersPerHour(1100);
        aircraftService.addAircraft(helicopter);
        WideBodyAirliner wideBodyAirliner = new WideBodyAirliner();
        wideBodyAirliner.setFuelConsumptionLitersPerHour(7500);
        aircraftService.addAircraft(wideBodyAirliner);
        Commuterliner commuterliner = new Commuterliner();
        commuterliner.setFuelConsumptionLitersPerHour(900);
        aircraftService.addAircraft(commuterliner);

        when(aircraftRepositoryMock.findByFuelConsumptionLitersPerHourBetween(1000, 4000))
                .thenReturn(company.getAircraftList()
                        .parallelStream()
                        .filter(aircraft ->
                                aircraft.getFuelConsumptionLitersPerHour().compareTo(1000) >= 0
                                        && aircraft.getFuelConsumptionLitersPerHour().compareTo(4000) <= 0)
                        .collect(Collectors.toList())
                );

        List<Aircraft> aircraftListCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters =
                aircraftService.findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters(1000, 4000);
        assertThat(aircraftListCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters.size(),
                is(1));
        assertThat(aircraftListCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters.get(0),
                is(helicopter));
    }

    @Test
    public void aircraftFactorySuccessfully() {
        Aircraft wideBodyAirliner = aircraftService.aircraftFactory(AircraftType.WIDE_BODY_AIRLINER);
        assertThat(wideBodyAirliner, instanceOf(Aircraft.class));
        assertThat(wideBodyAirliner, instanceOf(Airplane.class));
        assertThat(wideBodyAirliner, instanceOf(WideBodyAirliner.class));
        assertThat(aircraftService.aircraftFactory(AircraftType.COMMUTERLINER),
                instanceOf(Commuterliner.class));
        assertThat(aircraftService.aircraftFactory(AircraftType.HELICOPTER),
                instanceOf(Helicopter.class));
    }

    @Test
    public void updateTest() {
        aircraftService.update(1, "-1", -1, -1, -1, -1);
    }

    @Test
    public void searchTest() {
        aircraftService.search(1, 1, 1, 1);
    }
}
