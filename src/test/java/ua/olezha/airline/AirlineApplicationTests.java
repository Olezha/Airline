package ua.olezha.airline;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.shell.Shell;
import org.springframework.test.context.junit4.SpringRunner;
import ua.olezha.airline.model.aircraft.Aircraft;
import ua.olezha.airline.model.aircraft.AircraftType;
import ua.olezha.airline.model.company.Company;
import ua.olezha.airline.repository.CompanyRepository;
import ua.olezha.airline.service.AircraftService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@Import(AirlineApplicationRunner.class)
@SpringBootTest
public class AirlineApplicationTests implements ApplicationRunner {

    @Autowired
    private Shell shell;

    @Autowired
    private AircraftService aircraftService;

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
    }

    @Before
    public void before() {
        shell.evaluate(() -> "delete -all");
    }

    @Test
    public void shellHelpTest() {
        assertThat(shell.evaluate(() -> "help")).isNotNull();
    }

    @Test
    public void companyExistenceTest() {
        Assert.assertNotNull(companyRepository.findOne(1L));
    }

    @Test
    public void addAircraftSuccessfully() {
        Object firstOut = shell.evaluate(() -> "show");
        assertThat(shell.evaluate(() -> "show")).isEqualTo(firstOut);
        assertThat(shell.evaluate(() -> "add COMMUTERLINER")).isNull();
        assertThat(((List) shell.evaluate(() -> "show -raw")).size()).isEqualTo(1);
    }

    @Test
    public void addUnknownTypeAircraftTest() {
        Object firstOut = shell.evaluate(() -> "show");
        assertThat(shell.evaluate(() -> "show")).isEqualTo(firstOut);
        assertThat(shell.evaluate(() -> "add qwerty")).isNull();
        assertThat(shell.evaluate(() -> "show")).isEqualTo(firstOut);
    }

    @Test
    public void totalCapacityTest() {
        assertThat(shell.evaluate(() -> "tc -raw")).isEqualTo(0);
        shell.evaluate(() -> "mock");
        assertThat(shell.evaluate(() -> "tc -raw")).isEqualTo(3084);
    }

    @Test
    public void carryingCapacityTest() {
        assertThat(shell.evaluate(() -> "cc -raw")).isEqualTo(0);
        shell.evaluate(() -> "mock");
        assertThat(shell.evaluate(() -> "cc -raw")).isEqualTo(75924);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void aircraftSortedByFlightRangeTest() {
        shell.evaluate(() -> "mock");
        Object firstOut = shell.evaluate(() -> "show");
        assertThat(shell.evaluate(() -> "sort")).isNotEqualTo(firstOut);

        Aircraft lastAircraft = null;
        for (Aircraft aircraft : (List<Aircraft>) shell.evaluate(() -> "sort -raw")) {
            if (lastAircraft != null)
                assertThat(aircraft.getFlightRangeKm()).isGreaterThanOrEqualTo(lastAircraft.getFlightRangeKm());
            lastAircraft = aircraft;
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void aircraftSortedByFlightRangeDescTest() {
        shell.evaluate(() -> "mock");
        Object firstOut = shell.evaluate(() -> "show");
        assertThat(shell.evaluate(() -> "sort -desc")).isNotEqualTo(firstOut);

        Aircraft lastAircraft = null;
        for (Aircraft aircraft : (List<Aircraft>) shell.evaluate(() -> "sort -desc -raw")) {
            if (lastAircraft != null)
                assertThat(aircraft.getFlightRangeKm()).isLessThanOrEqualTo(lastAircraft.getFlightRangeKm());
            lastAircraft = aircraft;
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void airplanesCorrespondingToAGivenRangeOfFuelConsumptionParametersTest() {
        Object firstOut = shell.evaluate(() -> "show");
        assertThat(shell.evaluate(() -> "fuel 0 999999")).isEqualTo(firstOut);
        shell.evaluate(() -> "add COMMUTERLINER 0 1 2 3");
        assertThat(shell.evaluate(() -> "fuel 3 3")).isNotEqualTo(firstOut);

        for (Aircraft aircraft : (List<Aircraft>) shell.evaluate(() -> "fuel 70 120 -raw"))
            assertThat(aircraft.getFuelConsumptionLitersPerHour()).isBetween(70, 120);
    }

    @Test
    public void deleteWithoutAllFlagTest() {
        shell.evaluate(() -> "mock");
        Object firstOut = shell.evaluate(() -> "show");
        shell.evaluate(() -> "delete");
        assertThat(shell.evaluate(() -> "show")).isEqualTo(firstOut);
    }

    @Test
    public void deleteAllSuccessfully() {
        Object firstOut = shell.evaluate(() -> "show");
        shell.evaluate(() -> "add COMMUTERLINER 0 1 2 3");
        shell.evaluate(() -> "delete -all");
        assertThat(shell.evaluate(() -> "show")).isEqualTo(firstOut);
    }

    @Test
    public void searchByAnyParametersSuccessfully() {
        shell.evaluate(() -> "add COMMUTERLINER 0 1 2 3");
        shell.evaluate(() -> "add HELICOPTER 1 2 3 4");
        shell.evaluate(() -> "add WIDE_BODY_AIRLINER 2 3 4 5");
        shell.evaluate(() -> "add COMMUTERLINER 3 4 5 6");
        shell.evaluate(() -> "add HELICOPTER 4 5 6 7");
        shell.evaluate(() -> "add WIDE_BODY_AIRLINER 5 6 7 8");

        Object firstOut = shell.evaluate(() -> "search -seating-capacity 1");
        assertThat(shell.evaluate(() -> "search -carrying-capacity-kg 2")).isEqualTo(firstOut);
        assertThat(shell.evaluate(() -> "search -flight-range-km 3")).isEqualTo(firstOut);
        assertThat(shell.evaluate(() -> "search -fuel-consumption-liters-per-hour 4")).isEqualTo(firstOut);

        assertThat(((List) shell.evaluate(() -> "search -raw")).size()).isEqualTo(6);
        assertThat(((List) shell.evaluate(() -> "search -fuel-consumption-liters-per-hour 8 -raw")).size())
                .isEqualTo(1);
    }

    @Test
    public void entitiesToStringTest() {
        shell.evaluate(() -> "mock");
        List<Aircraft> aircraftList = aircraftService.allAircraftInTheAirline();
        for (Aircraft aircraft : aircraftList) {
            assertThat(aircraft.toString()).isNotBlank();
        }
    }

    @Test
    public void entitiesNotEqualsTest() {
        shell.evaluate(() -> "mock");
        List<Aircraft> aircraftList = aircraftService.allAircraftInTheAirline();
        Aircraft lastAircraft = null;
        for (Aircraft aircraft : aircraftList) {
            if (lastAircraft != null)
                assertThat(lastAircraft.equals(aircraft)).isFalse();
            lastAircraft = aircraft;
        }
    }

    @Test
    public void companyEqualsTest() {
        Company company = companyRepository.getOne(1L);
        Company sameCompany = companyRepository.getOne(1L);
        assertThat(company).isEqualTo(sameCompany);
    }

    @Test
    public void entitiesHasATypeTest() {
        shell.evaluate(() -> "mock");
        List<Aircraft> aircraftList = aircraftService.allAircraftInTheAirline();
        for (Aircraft aircraft : aircraftList) {
            assertThat(aircraft.getType()).isNotNull();
            assertThat(aircraft.getType()).isIn((Object[]) AircraftType.values());
        }
    }

    @Test
    public void testAircraftServiceRangeSearch() {
        Object firstOut = shell.evaluate(() -> "show");
        assertThat(shell.evaluate(() -> "range-search 1 2 3 4 5 6 7 8")).isEqualTo(firstOut);
        shell.evaluate(() -> "mock");
        shell.evaluate(() -> "range-search");

        for (int i = 2; i < 10000; i += i / 2) {
            List<Aircraft> aircraftList = aircraftService.search(i, -1, -1, -1, -1, -1, -1, -1);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getSeatingCapacity()).isGreaterThanOrEqualTo(i);

            aircraftList = aircraftService.search(-1, -1, -1, -1, i, -1, -1, -1);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getSeatingCapacity()).isLessThanOrEqualTo(i);

            aircraftList = aircraftService.search(i / 2, -1, -1, -1, 10000 - i / 2, -1, -1, -1);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getSeatingCapacity()).isBetween(i / 2, 10000 - i / 2);

            aircraftList = aircraftService.search(-1, i, -1, -1, -1, -1, -1, -1);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getCarryingCapacityKg()).isGreaterThanOrEqualTo(i);

            aircraftList = aircraftService.search(-1, -1, -1, -1, -1, i, -1, -1);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getCarryingCapacityKg()).isLessThanOrEqualTo(i);

            aircraftList = aircraftService.search(-1, i / 2, -1, -1, -1, 10000 - i / 2, -1, -1);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getCarryingCapacityKg()).isBetween(i / 2, 10000 - i / 2);

            aircraftList = aircraftService.search(-1, -1, i, -1, -1, -1, -1, -1);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getFlightRangeKm()).isGreaterThanOrEqualTo(i);

            aircraftList = aircraftService.search(-1, -1, -1, -1, -1, -1, i, -1);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getFlightRangeKm()).isLessThanOrEqualTo(i);

            aircraftList = aircraftService.search(-1, -1, i / 2, -1, -1, -1, 10000 - i / 2, -1);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getFlightRangeKm()).isBetween(i / 2, 10000 - i / 2);

            aircraftList = aircraftService.search(-1, -1, -1, i, -1, -1, -1, -1);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getFuelConsumptionLitersPerHour()).isGreaterThanOrEqualTo(i);

            aircraftList = aircraftService.search(-1, -1, -1, -1, -1, -1, -1, i);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getFuelConsumptionLitersPerHour()).isLessThanOrEqualTo(i);

            aircraftList = aircraftService.search(-1, -1, -1, i / 2, -1, -1, -1, 10000 - i / 2);
            for (Aircraft aircraft : aircraftList)
                assertThat(aircraft.getFuelConsumptionLitersPerHour()).isBetween(i / 2, 10000 - i / 2);
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testRangeSearch() {
        shell.evaluate(() -> "mock");
        List<Aircraft> aircraftList = (List<Aircraft>)
                shell.evaluate(() -> "range-search 10 90 600 -1 700 -raw");
        for (Aircraft aircraft : aircraftList) {
            assertThat(aircraft.getSeatingCapacity()).isBetween(10, 90);
            assertThat(aircraft.getCarryingCapacityKg()).isGreaterThanOrEqualTo(600);
            assertThat(aircraft.getFlightRangeKm()).isGreaterThanOrEqualTo(700);
        }
    }
}

@TestConfiguration
class AirlineApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        // Nothing to run
    }
}
