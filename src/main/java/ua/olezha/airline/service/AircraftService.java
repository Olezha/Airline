package ua.olezha.airline.service;

import ua.olezha.airline.model.Aircraft;

import java.util.List;

public interface AircraftService {

    void addAircraft(Aircraft aircraft);

    List<Aircraft> allAircraftInTheAirline();

    // TODO: what das total capacity mean
    int totalCapacityOfAllTheAircraftInTheAirline();

    int carryingCapacityOfAllTheAircraftInTheAirline();

    List<Aircraft> sortTheAircraftsByFlightRangeFromSmallerToLarger();

    List<Aircraft> findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParametersLitersPerHour(
            int from, int to);
}
