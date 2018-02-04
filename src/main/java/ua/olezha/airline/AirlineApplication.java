package ua.olezha.airline;

import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

@SpringBootApplication
public class AirlineApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(AirlineApplication.class, args);
    }

    @Bean
    public SessionFactory sessionFactory(
            EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.unwrap(SessionFactory.class);
    }
}
