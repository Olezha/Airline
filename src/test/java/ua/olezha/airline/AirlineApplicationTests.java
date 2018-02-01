package ua.olezha.airline;

import org.junit.Assert;
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
import ua.olezha.airline.model.company.Company;
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

    @Test
    public void shellHelpTest() {
        assertThat(shell.evaluate(() -> "help")).isNotNull();
    }

    @Test
    public void companyExistenceTest() {
        Assert.assertNotNull(companyRepository.findOne(1L));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void addAircraftSuccessfully() {
        int firstAircraftListSize = ((List<Aircraft>) shell.evaluate(() -> "show")).size();
        assertThat(shell.evaluate(() -> "add COMMUTERLINER")).isNull();
        Assert.assertThat(((List<Aircraft>) shell.evaluate(() -> "show")).size() - firstAircraftListSize,
                is(1));
    }

    @Test
    public void deleteAllSuccessfully() {
        shell.evaluate(() -> "add COMMUTERLINER 0 1 2 3");
        shell.evaluate(() -> "delete -all");
        Assert.assertThat(((List) shell.evaluate(() -> "show")).size(),
                is(0));
    }

    @Test
    public void searchByAnyParametersSuccessfully() {
        shell.evaluate(() -> "delete -all");

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

    @Test
    public void companyTestCoverage() {
        Company company = companyRepository.findOne(1L);
        // TODO: Oo
        Assert.assertNotEquals(company, companyRepository.findOne(1L));
    }
}

@TestConfiguration
class AirlineApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
    }
}
