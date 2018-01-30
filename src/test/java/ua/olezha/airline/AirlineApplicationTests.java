package ua.olezha.airline;

import org.junit.Assert;
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
import org.springframework.test.context.junit4.SpringRunner;
import ua.olezha.airline.model.aircraft.Aircraft;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(AirlineApplicationRunner.class)
public class AirlineApplicationTests implements ApplicationRunner {

    @Autowired
    private Shell shell;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
    }

    @Test
    public void shellHelpTest() {
        assertThat(shell.evaluate(() -> "help")).isNotNull();
    }

    @Test
    public void addAircraftSuccessfully() {
        int firstAircraftListSize = ((List<Aircraft>) shell.evaluate(() -> "show")).size();
        assertThat(shell.evaluate(() -> "add Commuterliner")).isNull();
        Assert.assertThat(((List<Aircraft>) shell.evaluate(() -> "show")).size() - firstAircraftListSize,
                is(1));
    }

    @Ignore
    @Test
    public void deleteAllSuccessfully() {
        shell.evaluate(() -> "delete -all");
        Assert.assertThat(((List) shell.evaluate(() -> "show")).size(),
                is(0));
    }

    @Ignore
    @Test
    public void searchByAnyParametersSuccessfully() {
        shell.evaluate(() -> "delete -all");

        shell.evaluate(() -> "add Commuterliner 0 1 2 3");
        shell.evaluate(() -> "add Helicopter 1 2 3 4");
        shell.evaluate(() -> "add WideBodyAirliner 2 3 4 5");
        shell.evaluate(() -> "add Commuterliner 3 4 5 6");
        shell.evaluate(() -> "add Helicopter 4 5 6 7");
        shell.evaluate(() -> "add WideBodyAirliner 5 6 7 8");

        assertThat(((List) shell.evaluate(() -> "search -seating-capacity 4")).size()).isEqualTo(1);
        assertThat(((List) shell.evaluate(() -> "search -carrying-capacity-kg 8")).size()).isEqualTo(1);
        assertThat(((List) shell.evaluate(() -> "search -flight-range-km 5")).size()).isEqualTo(1);
        assertThat(((List) shell.evaluate(() -> "search -fuel-consumption-liters-per-hour 3")).size()).isEqualTo(1);
    }
}

@TestConfiguration
class AirlineApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
    }
}
