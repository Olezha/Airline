package ua.olezha.airline.repository;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.olezha.airline.model.aircraft.Aircraft;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Slf4j
@Repository
public class AircraftRepositoryImpl implements BetweenExamplesRepository<Aircraft> {

    private final SessionFactory sessionFactory;

    @Autowired
    public AircraftRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Aircraft> findAll(Aircraft fromExampleEntity,
                                  Aircraft toExampleEntity) {
        Criteria criteria = sessionFactory
                .getCurrentSession()
                .createCriteria(Aircraft.class);

        if (fromExampleEntity.getSeatingCapacity() != null
                && toExampleEntity.getSeatingCapacity() != null) {
            criteria.add(Restrictions.between(
                    "seatingCapacity",
                    fromExampleEntity.getSeatingCapacity(),
                    toExampleEntity.getSeatingCapacity()));
        } else {
            if (fromExampleEntity.getSeatingCapacity() != null) {
                criteria.add(Restrictions.ge(
                        "seatingCapacity",
                        fromExampleEntity.getSeatingCapacity()));
            } else if (toExampleEntity.getSeatingCapacity() != null) {
                criteria.add(Restrictions.le(
                        "seatingCapacity",
                        toExampleEntity.getSeatingCapacity()));
            }
        }

        if (fromExampleEntity.getCarryingCapacityKg() != null
                && toExampleEntity.getCarryingCapacityKg() != null) {
            criteria.add(Restrictions.between(
                    "carryingCapacityKg",
                    fromExampleEntity.getCarryingCapacityKg(),
                    toExampleEntity.getCarryingCapacityKg()));
        } else {
            if (fromExampleEntity.getCarryingCapacityKg() != null) {
                criteria.add(Restrictions.ge(
                        "carryingCapacityKg",
                        fromExampleEntity.getCarryingCapacityKg()));
            } else if (toExampleEntity.getCarryingCapacityKg() != null) {
                criteria.add(Restrictions.le(
                        "carryingCapacityKg",
                        toExampleEntity.getCarryingCapacityKg()));
            }
        }

        if (fromExampleEntity.getFlightRangeKm() != null
                && toExampleEntity.getFlightRangeKm() != null) {
            criteria.add(Restrictions.between(
                    "flightRangeKm",
                    fromExampleEntity.getFlightRangeKm(),
                    toExampleEntity.getFlightRangeKm()));
        } else {
            if (fromExampleEntity.getFlightRangeKm() != null) {
                criteria.add(Restrictions.ge(
                        "flightRangeKm",
                        fromExampleEntity.getSeatingCapacity()));
            } else if (toExampleEntity.getFlightRangeKm() != null) {
                criteria.add(Restrictions.le(
                        "flightRangeKm",
                        toExampleEntity.getFlightRangeKm()));
            }
        }

        if (fromExampleEntity.getFuelConsumptionLitersPerHour() != null
                && toExampleEntity.getFuelConsumptionLitersPerHour() != null) {
            criteria.add(Restrictions.between(
                    "fuelConsumptionLitersPerHour",
                    fromExampleEntity.getFuelConsumptionLitersPerHour(),
                    toExampleEntity.getFuelConsumptionLitersPerHour()));
        } else {
            if (fromExampleEntity.getFuelConsumptionLitersPerHour() != null) {
                criteria.add(Restrictions.ge(
                        "fuelConsumptionLitersPerHour",
                        fromExampleEntity.getFuelConsumptionLitersPerHour()));
            } else if (toExampleEntity.getFuelConsumptionLitersPerHour() != null) {
                criteria.add(Restrictions.le(
                        "fuelConsumptionLitersPerHour",
                        toExampleEntity.getFuelConsumptionLitersPerHour()));
            }
        }

        return criteria.list();
    }
}
