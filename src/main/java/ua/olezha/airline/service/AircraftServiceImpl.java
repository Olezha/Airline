package ua.olezha.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.olezha.airline.model.aircraft.Aircraft;
import ua.olezha.airline.model.aircraft.Commuterliner;
import ua.olezha.airline.model.aircraft.Helicopter;
import ua.olezha.airline.model.aircraft.WideBodyAirliner;
import ua.olezha.airline.model.company.Company;
import ua.olezha.airline.repository.AircraftRepository;
import ua.olezha.airline.repository.CompanyRepository;

import javax.annotation.PostConstruct;
import static java.util.Comparator.*;
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
        company.getAircraftList().add(aircraft);
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
    public Aircraft aircraftFactory(String type) {
        try {
            Class<?> aircraftClass = Class.forName("ua.olezha.airline.model.aircraft." + type);
            return  (Aircraft) aircraftClass.newInstance();
        } catch (ClassNotFoundException | ClassCastException | InstantiationException| IllegalAccessException e) {
            throw new IllegalArgumentException(type + " is an unknown type of aircraft");
        }
    }

    @Override
    public void deleteAll() {
        aircraftRepository.deleteAll();
    }

    @Override
    public List<Aircraft> search(
            int seatingCapacity, int carryingCapacityKg, int flightRangeKm, int fuelConsumptionLitersPerHour) {
        /**
         * Aircraft aircraft = new Aircraft() {};
         * doesn't work
         * org.springframework.dao.InvalidDataAccessApiUsageException: Not an entity
         */

        Commuterliner commuterliner = new Commuterliner();
        Helicopter helicopter = new Helicopter();
        WideBodyAirliner wideBodyAirliner = new WideBodyAirliner();

        if (seatingCapacity != -1) {
            commuterliner.setSeatingCapacity(seatingCapacity);
            helicopter.setSeatingCapacity(seatingCapacity);
            wideBodyAirliner.setSeatingCapacity(seatingCapacity);
        }
        if (carryingCapacityKg != -1) {
            commuterliner.setCarryingCapacityKg(carryingCapacityKg);
            helicopter.setCarryingCapacityKg(carryingCapacityKg);
            wideBodyAirliner.setCarryingCapacityKg(carryingCapacityKg);
        }
        if (flightRangeKm != -1) {
            commuterliner.setFlightRangeKm(flightRangeKm);
            helicopter.setFlightRangeKm(flightRangeKm);
            wideBodyAirliner.setFlightRangeKm(flightRangeKm);
        }
        if (fuelConsumptionLitersPerHour != -1) {
            commuterliner.setFuelConsumptionLitersPerHour(fuelConsumptionLitersPerHour);
            helicopter.setFuelConsumptionLitersPerHour(fuelConsumptionLitersPerHour);
            wideBodyAirliner.setFuelConsumptionLitersPerHour(fuelConsumptionLitersPerHour);
        }

        ExampleMatcher aircraftExampleMatcher = ExampleMatcher.matching()
                .withIgnoreNullValues();

        Example<Aircraft> commuterlinerExample = Example.of(commuterliner, aircraftExampleMatcher);
        List<Aircraft> aircraftList = aircraftRepository.findAll(commuterlinerExample);

        Example<Aircraft> helicopterExample = Example.of(helicopter, aircraftExampleMatcher);
        aircraftList.addAll(aircraftRepository.findAll(helicopterExample));

        Example<Aircraft> wideBodyAirlinerExample = Example.of(wideBodyAirliner, aircraftExampleMatcher);
        aircraftList.addAll(aircraftRepository.findAll(wideBodyAirlinerExample));

        return aircraftList;
    }
}
