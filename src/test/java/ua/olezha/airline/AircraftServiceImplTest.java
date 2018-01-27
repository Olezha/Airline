package ua.olezha.airline;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.olezha.airline.model.*;
import ua.olezha.airline.repository.AircraftRepository;
import ua.olezha.airline.repository.CompanyRepository;
import ua.olezha.airline.service.AircraftService;
import ua.olezha.airline.service.AircraftServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

@Slf4j
public class AircraftServiceImplTest {

    private Company company;
    private AircraftService aircraftService;
    private AircraftRepository aircraftRepositoryMock;

    @Before
    public void setUp() {
        company = new Company();
        CompanyRepository companyRepositoryMock =
                Mockito.mock(CompanyRepository.class);
        when(companyRepositoryMock.getOne(1L)).thenReturn(company);
        aircraftRepositoryMock = Mockito.mock(AircraftRepository.class);
        aircraftService = new AircraftServiceImpl(
                aircraftRepositoryMock, companyRepositoryMock);
    }

    @Test
    public void addAircraftSuccessfully() throws Exception {
        aircraftService.addAircraft(new Commuterliner());
        assertNotNull(company.getAircraftList().get(0));
    }

    @Test
    public void getAllAircraftInTheAirlineSuccessfully() throws Exception {
        aircraftService.addAircraft(new Helicopter());
        aircraftService.addAircraft(new WideBodyAirliner());
        assertThat(company.getAircraftList().size(), is(2));
        aircraftService.addAircraft(new Commuterliner());
        aircraftService.addAircraft(new Commuterliner());
        assertThat(company.getAircraftList().size(), is(4));
    }

    @Test
    public void testTotalCapacityOfAllTheAircraftInTheAirline() throws Exception {
        Helicopter helicopter = new Helicopter();
        helicopter.setSeatingCapacity(18);
        aircraftService.addAircraft(helicopter);
        Commuterliner commuterliner = new Commuterliner();
        commuterliner.setSeatingCapacity(80);
        aircraftService.addAircraft(commuterliner);
        WideBodyAirliner wideBodyAirliner = new WideBodyAirliner();
        wideBodyAirliner.setSeatingCapacity(450);
        aircraftService.addAircraft(wideBodyAirliner);
        Helicopter helicopter2 = new Helicopter();
        helicopter2.setSeatingCapacity(8);
        aircraftService.addAircraft(helicopter2);

        when(aircraftRepositoryMock.totalCapacityOfAllTheAircraftInTheAirline())
                .thenReturn(company.getAircraftList()
                        .parallelStream()
                        .mapToInt(Aircraft::getSeatingCapacity).sum()
                );

        assertThat(aircraftService.totalCapacityOfAllTheAircraftInTheAirline(),
                is(556));
    }

    @Test
    public void testCarryingCapacityOfAllTheAircraftInTheAirline() throws Exception {
        Helicopter helicopter = new Helicopter();
        helicopter.setCarryingCapacityKg(5000);
        aircraftService.addAircraft(helicopter);
        WideBodyAirliner wideBodyAirliner = new WideBodyAirliner();
        wideBodyAirliner.setCarryingCapacityKg(180000);
        aircraftService.addAircraft(wideBodyAirliner);

        when(aircraftRepositoryMock.carryingCapacityOfAllTheAircraftInTheAirline())
                .thenReturn(company.getAircraftList()
                        .parallelStream()
                        .mapToInt(Aircraft::getCarryingCapacityKg).sum()
                );

        assertThat(aircraftService.carryingCapacityOfAllTheAircraftInTheAirline(),
                is(185000));
    }

    @Test
    public void testSortTheAircraftsByFlightRangeFromSmallerToLarger() throws Exception {
        Helicopter helicopter = new Helicopter();
        helicopter.setFlightRangeKm(800);
        aircraftService.addAircraft(helicopter);
        WideBodyAirliner wideBodyAirliner = new WideBodyAirliner();
        wideBodyAirliner.setFlightRangeKm(11000);
        aircraftService.addAircraft(wideBodyAirliner);

        List<Aircraft> aircraftListSortedByFlightRangeFromSmallerToLarger =
                aircraftService.sortTheAircraftsByFlightRangeFromSmallerToLarger();
        assertTrue(
                aircraftListSortedByFlightRangeFromSmallerToLarger.get(0).getFlightRangeKm()
                        <
                        aircraftListSortedByFlightRangeFromSmallerToLarger.get(1).getFlightRangeKm());
    }

    @Test
    public void findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParametersLitersPerHourSuccessfully() throws Exception {
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
                aircraftService.findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParametersLitersPerHour(1000, 4000);
        assertTrue(aircraftListCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters.size() == 1);
        assertThat(aircraftListCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters.get(0),
                is(helicopter));
    }
}
