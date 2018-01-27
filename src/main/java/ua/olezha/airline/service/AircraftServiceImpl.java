package ua.olezha.airline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.olezha.airline.model.Aircraft;
import ua.olezha.airline.model.Company;
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
    public List<Aircraft> sortTheAircraftsByFlightRangeFromSmallerToLarger() {
        return allAircraftInTheAirline()
                .stream()
                .sorted(comparing(Aircraft::getFlightRangeKm, nullsFirst(naturalOrder())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Aircraft> findAircraftCorrespondingToTheSpecifiedRangeOfFuelConsumptionParametersLitersPerHour(
            int fromLitersPerHour, int toLitersPerHour) {
        return aircraftRepository.findByFuelConsumptionLitersPerHourBetween(fromLitersPerHour, toLitersPerHour);
    }
}
