package ua.olezha.airline;

import asg.cliche.ShellFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class AirlineApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext context =
                SpringApplication.run(AirlineApplication.class, args);

        ShellFactory
                .createConsoleShell("", "Airline",
                        context.getBean(AirlineController.class))
                .commandLoop();
    }
}
