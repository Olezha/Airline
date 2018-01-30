package ua.olezha.airline.service;

import ua.olezha.airline.model.aircraft.Aircraft;

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

    Aircraft aircraftFactory(String type);

    void deleteAll();
}
