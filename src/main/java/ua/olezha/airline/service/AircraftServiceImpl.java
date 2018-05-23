package ua.olezha.airline.service;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.olezha.airline.model.aircraft.*;
import ua.olezha.airline.model.company.Company;
import ua.olezha.airline.repository.AircraftRepository;
import ua.olezha.airline.repository.CompanyRepository;

import javax.annotation.PostConstruct;
import static java.util.Comparator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AircraftServiceImpl implements AircraftService {

    private final AircraftRepository aircraftRepository;

    private final CompanyRepository companyRepository;

    public AircraftServiceImpl(AircraftRepository aircraftRepository, CompanyRepository companyRepository) {
        this.aircraftRepository = aircraftRepository;
        this.companyRepository = companyRepository;
    }

    @PostConstruct
    protected void initialize() {
        if (companyRepository.count() < 1)
            companyRepository.save(new Company());
    }

    @Override
    @Transactional
    public void addAircraft(Aircraft aircraft) {
        aircraftRepository.save(aircraft);
        companyRepository.getOne(1L).addAircraft(aircraft);
    }

    @Override
    public List<Aircraft> allAircraftInTheAirline() {
        return companyRepository.getOne(1L).getAircraftList();
    }

    @Override
    public int totalCapacityOfAllTheAircraftInTheAirline() {
        return aircraftRepository.totalCapacityOfAllTheAircraftInTheAirline();
    }

    @Override
    public int carryingCapacityOfAllTheAircraftInTheAirline() {
        return aircraftRepository.carryingCapacityOfAllTheAircraftInTheAirline();
    }

    @Override
    public List<Aircraft> sortTheAircraftByFlightRangeFromSmallerToLarger() {
        return allAircraftInTheAirline()
                .stream()
                .sorted(comparing(Aircraft::getFlightRangeKm, nullsFirst(naturalOrder())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Aircraft> findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParameters(
            int fromLitersPerHour, int toLitersPerHour) {
        return aircraftRepository.findByFuelConsumptionLitersPerHourBetween(fromLitersPerHour, toLitersPerHour);
    }

    @Override
    public Aircraft aircraftFactory(AircraftType aircraftType) {
        try {
            return  (Aircraft) aircraftType.getAircraftClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new IllegalArgumentException(aircraftType + " is an unknown type of aircraft");
        }
    }

    @Override
    public void deleteAll() {
        aircraftRepository.deleteAll();
    }

    @Override
    public List<Aircraft> search(
            int seatingCapacity, int carryingCapacityKg, int flightRangeKm, int fuelConsumptionLitersPerHour) {
        /*
         * Aircraft aircraft = new Aircraft() {};
         * org.springframework.dao.InvalidDataAccessApiUsageException: Not an entity: AircraftServiceImpl$1;
         */

        List<Aircraft> aircraftList = new ArrayList<>();

        for (AircraftType aircraftType : AircraftType.values()) {
                Example<Aircraft> aircraftExample = Example.of(
                        exampleAircraftFactory(aircraftType,
                                seatingCapacity, carryingCapacityKg,
                                flightRangeKm, fuelConsumptionLitersPerHour));

                aircraftList.addAll(aircraftRepository.findAll(aircraftExample));
        }

        return aircraftList;
    }

    @Override
    public void delete(int id) {
        aircraftRepository.deleteById((long) id);
    }

    @Override
    @Transactional
    public void update(int id, String name, int seatingCapacity, int carryingCapacityKg, int flightRangeKm, int fuelConsumptionLitersPerHour) {
        Aircraft aircraft = aircraftRepository.getOne((long) id);
        if (!"-1".equals(name))
            aircraft.setName(name);
        setProperties(aircraft, seatingCapacity, carryingCapacityKg, flightRangeKm, fuelConsumptionLitersPerHour);
    }

    private Aircraft exampleAircraftFactory(AircraftType aircraftType,
                                            int seatingCapacity, int carryingCapacityKg,
                                            int flightRangeKm, int fuelConsumptionLitersPerHour) {
        Aircraft aircraft;
        if (aircraftType != null)
            aircraft = aircraftFactory(aircraftType);
        else
            aircraft = new Aircraft() {
                @Override
                public AircraftType getType() {
                    return null;
                }
            };
        aircraft.setCreated(null);

        setProperties(aircraft, seatingCapacity, carryingCapacityKg, flightRangeKm, fuelConsumptionLitersPerHour);

        return aircraft;
    }

    private void setProperties(Aircraft aircraft,
                               int seatingCapacity, int carryingCapacityKg,
                               int flightRangeKm, int fuelConsumptionLitersPerHour) {
        if (seatingCapacity >= 0)
            aircraft.setSeatingCapacity(seatingCapacity);
        if (carryingCapacityKg >= 0)
            aircraft.setCarryingCapacityKg(carryingCapacityKg);
        if (flightRangeKm >= 0)
            aircraft.setFlightRangeKm(flightRangeKm);
        if (fuelConsumptionLitersPerHour >= 0)
            aircraft.setFuelConsumptionLitersPerHour(fuelConsumptionLitersPerHour);
    }
}
