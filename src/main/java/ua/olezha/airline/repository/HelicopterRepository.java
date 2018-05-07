package ua.olezha.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.olezha.airline.model.aircraft.Helicopter;

public interface HelicopterRepository extends JpaRepository<Helicopter, Long> {
}
