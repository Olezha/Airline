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
import ua.olezha.airline.model.aircraft.AircraftType;
import ua.olezha.airline.model.company.Company;
import ua.olezha.airline.repository.CompanyRepository;
import ua.olezha.airline.service.AircraftService;

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
        assertThat(shell.evaluate(() -> "show")).isNotEqualTo(firstOut);
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

    @Test
    public void aircraftSortedByFlightRangeTest() {
        shell.evaluate(() -> "mock");
        Object firstOut = shell.evaluate(() -> "show");
        assertThat(shell.evaluate(() -> "sort")).isNotEqualTo(firstOut);
    }

    @Test
    public void aircraftSortedByFlightRangeDescTest() {
        shell.evaluate(() -> "mock");
        Object firstOut = shell.evaluate(() -> "show");
        assertThat(shell.evaluate(() -> "sort -desc")).isNotEqualTo(firstOut);
    }

    @Test
    public void airplanesCorrespondingToAGivenRangeOfFuelConsumptionParametersTest() {
        Object firstOut = shell.evaluate(() -> "show");
        assertThat(shell.evaluate(() -> "fuel 0 999999")).isEqualTo(firstOut);
        shell.evaluate(() -> "add COMMUTERLINER 0 1 2 3");
        assertThat(shell.evaluate(() -> "fuel 3 3")).isNotEqualTo(firstOut);
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
            assertThat(aircraft.getType()).isIn(AircraftType.values());
        }
    }
}

@TestConfiguration
class AirlineApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
    }
}
