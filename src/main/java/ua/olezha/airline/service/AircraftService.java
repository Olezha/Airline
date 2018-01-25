package ua.olezha.airline.service;

import ua.olezha.airline.model.Aircraft;

import java.util.List;

public interface AircraftService {

    // TODO: what das total capacity mean
    int totalCapacityOfAllTheAircraftInTheAirline();

    // TODO: what das carrying capacity mean
    int carryingCapacityOfAllTheAircraftInTheAirline();

    List<Aircraft> sortTheAircraftsByFlightRangeFromSmallerToLarger();

    List<Aircraft> findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParametersLitersPerHour(
            int from, int to);
}
