package ua.olezha.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ua.olezha.airline.model.aircraft.Aircraft;

import java.util.List;

// TODO
@Repository
//@RepositoryRestResource(path = "aircraft", collectionResourceRel = "aircrafts")
public interface AircraftRepository
        extends JpaRepository<Aircraft, Long>, BetweenExamplesRepository<Aircraft> {

    @Query(value =
            "SELECT COALESCE(a, 0) + COALESCE(b, 0) + COALESCE(c, 0) " +
                    "FROM " +
                    " ( SELECT SUM(SEATING_CAPACITY) a FROM  COMMUTERLINER b " +
                    "   RIGHT JOIN COMPANY_AIRCRAFT_LIST c ON b.ID = c.AIRCRAFT_ID ) a " +
                    "CROSS JOIN " +
                    " ( SELECT SUM(SEATING_CAPACITY) b FROM  WIDE_BODY_AIRLINER b " +
                    "   RIGHT JOIN COMPANY_AIRCRAFT_LIST c ON b.ID = c.AIRCRAFT_ID ) a " +
                    "CROSS JOIN " +
                    " ( SELECT SUM(SEATING_CAPACITY) c FROM  HELICOPTER b " +
                    "   RIGHT JOIN COMPANY_AIRCRAFT_LIST c ON b.ID = c.AIRCRAFT_ID ) a",
            nativeQuery = true)
    int totalCapacityOfAllTheAircraftInTheAirline();

    @Query(value =
            "SELECT COALESCE(a, 0) + COALESCE(b, 0) + COALESCE(c, 0) " +
                    "FROM " +
                    " ( SELECT SUM(CARRYING_CAPACITY_KG) a FROM  COMMUTERLINER b " +
                    "   RIGHT JOIN COMPANY_AIRCRAFT_LIST c ON b.ID = c.AIRCRAFT_ID ) a " +
                    "CROSS JOIN " +
                    " ( SELECT SUM(CARRYING_CAPACITY_KG) b FROM  WIDE_BODY_AIRLINER b " +
                    "   RIGHT JOIN COMPANY_AIRCRAFT_LIST c ON b.ID = c.AIRCRAFT_ID ) a " +
                    "CROSS JOIN " +
                    " ( SELECT SUM(CARRYING_CAPACITY_KG) c FROM  HELICOPTER b " +
                    "   RIGHT JOIN COMPANY_AIRCRAFT_LIST c ON b.ID = c.AIRCRAFT_ID ) a",
            nativeQuery = true)
    int carryingCapacityOfAllTheAircraftInTheAirline();

    List<Aircraft> findByFuelConsumptionLitersPerHourBetween(int fromLitersPerHour, int toLitersPerHour);
}
