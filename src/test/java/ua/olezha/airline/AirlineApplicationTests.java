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
}

@TestConfiguration
class AirlineApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
    }
}
