package ua.olezha.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.olezha.airline.model.Aircraft;

@Repository
public interface AircraftRepository
        extends JpaRepository<Aircraft, Long> {
}
