package ua.olezha.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.olezha.airline.model.AirlineCompany;

@Repository
public interface AirlineCompanyRepository
        extends JpaRepository<AirlineCompany, Long> {
}
