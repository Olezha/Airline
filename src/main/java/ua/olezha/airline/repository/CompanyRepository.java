package ua.olezha.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.olezha.airline.model.company.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
