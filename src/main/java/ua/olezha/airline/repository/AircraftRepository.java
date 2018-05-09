package ua.olezha.airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.olezha.airline.model.aircraft.Aircraft;

import java.util.List;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    @Query(name = "aircraft.totalCapacityOfAllTheAircraftInTheAirline", nativeQuery = true)
    int totalCapacityOfAllTheAircraftInTheAirline();

    @Query(name = "aircraft.carryingCapacityOfAllTheAircraftInTheAirline", nativeQuery = true)
    int carryingCapacityOfAllTheAircraftInTheAirline();

    List<Aircraft> findByFuelConsumptionLitersPerHourBetween(int fromLitersPerHour, int toLitersPerHour);
}
