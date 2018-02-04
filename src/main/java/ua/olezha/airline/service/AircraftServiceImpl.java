package ua.olezha.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
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
        Company company = companyRepository.getOne(1L);
        company.addAircraft(aircraft);
        companyRepository.save(company);
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
                        exampleEntityFactory(aircraftType,
                                seatingCapacity, carryingCapacityKg,
                                flightRangeKm, fuelConsumptionLitersPerHour));

                aircraftList.addAll(aircraftRepository.findAll(aircraftExample));
        }

        return aircraftList;
    }

    @Override
    public List<Aircraft> search(int fromSeatingCapacity, int fromCarryingCapacityKg,
                                 int fromFlightRangeKm, int fromFuelConsumptionLitersPerHour,
                                 int toSeatingCapacity, int toCarryingCapacityKg,
                                 int toFlightRangeKm, int toFuelConsumptionLitersPerHour) {
        List<Aircraft> aircraftList = new ArrayList<>();

        for (AircraftType aircraftType : AircraftType.values()) {
            Aircraft fromAircraft =  exampleEntityFactory(aircraftType,
                    fromSeatingCapacity, fromCarryingCapacityKg,
                    fromFlightRangeKm, fromFuelConsumptionLitersPerHour);
            Aircraft toAircraft =  exampleEntityFactory(aircraftType,
                    toSeatingCapacity, toCarryingCapacityKg,
                    toFlightRangeKm, toFuelConsumptionLitersPerHour);

            aircraftList.addAll(aircraftRepository.findAll(fromAircraft, toAircraft));
        }

        return aircraftList;
    }

    private Aircraft exampleEntityFactory(AircraftType aircraftType,
                                          int seatingCapacity, int carryingCapacityKg,
                                          int flightRangeKm, int fuelConsumptionLitersPerHour) {
        Aircraft aircraft = null;
        try {
            aircraft = (Aircraft) aircraftType.getAircraftClass().newInstance();

            if (seatingCapacity >= 0)
                aircraft.setSeatingCapacity(seatingCapacity);
            if (carryingCapacityKg >= 0)
                aircraft.setCarryingCapacityKg(carryingCapacityKg);
            if (flightRangeKm >= 0)
                aircraft.setFlightRangeKm(flightRangeKm);
            if (fuelConsumptionLitersPerHour >= 0)
                aircraft.setFuelConsumptionLitersPerHour(fuelConsumptionLitersPerHour);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return aircraft;
    }
}
