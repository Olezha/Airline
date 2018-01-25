package ua.olezha.airline.service;

import org.springframework.stereotype.Service;
import ua.olezha.airline.model.Aircraft;

import java.util.List;

@Service
public class AircraftServiceImpl implements AircraftService {

    @Override
    public int totalCapacityOfAllTheAircraftInTheAirline() {
        return 0;
    }

    @Override
    public int carryingCapacityOfAllTheAircraftInTheAirline() {
        return 0;
    }

    @Override
    public List<Aircraft> sortTheAircraftsByFlightRangeFromSmallerToLarger() {
        return null;
    }

    @Override
    public List<Aircraft> findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParametersLitersPerHour(
            int from, int to) {
        return null;
    }
}
