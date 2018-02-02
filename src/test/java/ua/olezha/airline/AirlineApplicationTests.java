package ua.olezha.airline;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.shell.Shell;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ua.olezha.airline.model.aircraft.Aircraft;
import ua.olezha.airline.repository.CompanyRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Import(AirlineApplicationRunner.class)
public class AirlineApplicationTests implements ApplicationRunner {

    @Autowired
    private Shell shell;

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

    @Ignore
    @Test
    @SuppressWarnings("unchecked")
    public void addAircraftSuccessfully() {
        int firstAircraftListSize = ((List<Aircraft>) shell.evaluate(() -> "show")).size();
        assertThat(shell.evaluate(() -> "add COMMUTERLINER")).isNull();
        Assert.assertThat(((List<Aircraft>) shell.evaluate(() -> "show")).size() - firstAircraftListSize,
                is(1));
    }

    @Test
    public void totalCapacityTest() {
        assertThat(shell.evaluate(() -> "tc")).isEqualTo(0);
        shell.evaluate(() -> "mock");
        assertThat(shell.evaluate(() -> "tc")).isEqualTo(3084);
    }

    @Test
    public void carryingCapacityTest() {
        assertThat(shell.evaluate(() -> "cc")).isEqualTo(0);
        shell.evaluate(() -> "mock");
        assertThat(shell.evaluate(() -> "cc")).isEqualTo(75924);
    }

    @Ignore
    @Test
    @SuppressWarnings("unchecked")
    public void aircraftSortedByFlightRangeTest() {
        shell.evaluate(() -> "mock");
        List<Aircraft> aircraftList = (List<Aircraft>) shell.evaluate(() -> "sort");
        Aircraft lastAircraft = null;
        for (Aircraft aircraft : aircraftList) {
            if (lastAircraft != null)
                assertThat(aircraft.getFlightRangeKm())
                        .isGreaterThan(lastAircraft.getFlightRangeKm());
            lastAircraft = aircraft;
        }
    }

    @Ignore
    @Test
    @SuppressWarnings("unchecked")
    public void aircraftSortedByFlightRangeDescTest() {
        shell.evaluate(() -> "mock");
        List<Aircraft> aircraftList = (List<Aircraft>) shell.evaluate(() -> "sort -desc");
        Aircraft lastAircraft = null;
        for (Aircraft aircraft : aircraftList) {
            if (lastAircraft != null)
                assertThat(lastAircraft.getFlightRangeKm())
                        .isGreaterThan(aircraft.getFlightRangeKm());
            lastAircraft = aircraft;
        }
    }

    @Ignore
    @Test
    public void airplanesCorrespondingToAGivenRangeOfFuelConsumptionParametersTest() {
        assertThat(((List) shell.evaluate(() -> "fuel 0 999999")).size())
                .isEqualTo(0);
        shell.evaluate(() -> "add COMMUTERLINER 0 1 2 3");
        assertThat(((List) shell.evaluate(() -> "fuel 3 3")).size())
                .isEqualTo(1);
    }

    @Ignore
    @Test
    public void deleteWithoutAllFlagTest() {
        shell.evaluate(() -> "mock");
        int size = ((List) shell.evaluate(() -> "show")).size();
        shell.evaluate(() -> "delete");
        assertThat(((List) shell.evaluate(() -> "show")).size())
                .isEqualTo(size);
    }

    @Ignore
    @Test
    public void deleteAllSuccessfully() {
        shell.evaluate(() -> "add COMMUTERLINER 0 1 2 3");
        shell.evaluate(() -> "delete -all");
        Assert.assertThat(((List) shell.evaluate(() -> "show")).size(),
                is(0));
    }

    @Ignore
    @Test
    public void searchByAnyParametersSuccessfully() {
        shell.evaluate(() -> "add COMMUTERLINER 0 1 2 3");
        shell.evaluate(() -> "add HELICOPTER 1 2 3 4");
        shell.evaluate(() -> "add WIDE_BODY_AIRLINER 2 3 4 5");
        shell.evaluate(() -> "add COMMUTERLINER 3 4 5 6");
        shell.evaluate(() -> "add HELICOPTER 4 5 6 7");
        shell.evaluate(() -> "add WIDE_BODY_AIRLINER 5 6 7 8");

        assertThat(((List) shell.evaluate(() -> "search -seating-capacity 4")).size()).isEqualTo(1);
        assertThat(((List) shell.evaluate(() -> "search -carrying-capacity-kg 6")).size()).isEqualTo(1);
        assertThat(((List) shell.evaluate(() -> "search -flight-range-km 5")).size()).isEqualTo(1);
        assertThat(((List) shell.evaluate(() -> "search -fuel-consumption-liters-per-hour 3")).size()).isEqualTo(1);
    }

    @Ignore
    @Test
    @SuppressWarnings("unchecked")
    public void entitiesToStringTest() {
        shell.evaluate(() -> "mock");
        List<Aircraft> aircraftList = (List<Aircraft>) shell.evaluate(() -> "sort -desc");
        for (Aircraft aircraft : aircraftList) {
            assertThat(aircraft.toString()).isNotBlank();
        }
    }

    @Ignore
    @Test
    @SuppressWarnings("unchecked")
    public void entitiesNotEqualsTest() {
        shell.evaluate(() -> "mock");
        List<Aircraft> aircraftList = (List<Aircraft>) shell.evaluate(() -> "sort -desc");
        Aircraft lastAircraft = null;
        for (Aircraft aircraft : aircraftList) {
            if (lastAircraft != null)
                assertThat(lastAircraft.equals(aircraft)).isFalse();
            lastAircraft = aircraft;
        }
    }
}

@TestConfiguration
class AirlineApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
    }
}
