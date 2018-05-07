package ua.olezha.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.olezha.airline.model.aircraft.WideBodyAirliner;

public interface WideBodyAirlinerRepository extends JpaRepository<WideBodyAirliner, Long> {
}
