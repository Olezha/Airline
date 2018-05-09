package ua.olezha.airline.service;

import ua.olezha.airline.model.aircraft.Aircraft;
import ua.olezha.airline.model.aircraft.AircraftType;

import java.util.List;

public interface AircraftService {

    void addAircraft(Aircraft aircraft);

    List<Aircraft> allAircraftInTheAirline();

    // Passenger capacity
    int totalCapacityOfAllTheAircraftInTheAirline();

    int carryingCapacityOfAllTheAircraftInTheAirline();

    List<Aircraft> sortTheAircraftByFlightRangeFromSmallerToLarger();

    List<Aircraft> findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters(
            int fromLitersPerHour, int toLitersPerHour);

    Aircraft aircraftFactory(AircraftType aircraftType);

    void deleteAll();

    List<Aircraft> search(
            int seatingCapacity, int carryingCapacityKg, int flightRangeKm, int fuelConsumptionLitersPerHour);

    void delete(int id);

    void update(int id, String name, int seatingCapacity, int carryingCapacityKg, int flightRangeKm, int fuelConsumptionLitersPerHour);
}
