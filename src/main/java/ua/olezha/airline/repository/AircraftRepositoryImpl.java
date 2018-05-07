package ua.olezha.airline.repository;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ua.olezha.airline.model.aircraft.Aircraft;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AircraftRepositoryImpl implements BetweenExamplesRepository<Aircraft> {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public AircraftRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    // TODO
    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Aircraft> findAll(Aircraft fromExampleEntity,
                                  Aircraft toExampleEntity) {
        Criteria criteria = entityManagerFactory.unwrap(SessionFactory.class)
                .getCurrentSession()
                .createCriteria(Aircraft.class);

        addCriteriaForSeatingCapacity(criteria,
                fromExampleEntity.getSeatingCapacity(), toExampleEntity.getSeatingCapacity());

        addCriteriaForCarryingCapacityKg(criteria,
                fromExampleEntity.getCarryingCapacityKg(), toExampleEntity.getCarryingCapacityKg());

        addCriteriaForFlightRangeKm(criteria,
                fromExampleEntity.getFlightRangeKm(), toExampleEntity.getFlightRangeKm());

        addCriteriaForFuelConsumptionLitersPerHour(criteria,
                fromExampleEntity.getFuelConsumptionLitersPerHour(),
                toExampleEntity.getFuelConsumptionLitersPerHour());

        return criteria.list();
    }

    private void addCriteriaForSeatingCapacity(Criteria criteria,
                                               Integer from, Integer to) {
        if (from != null && to != null) {
            criteria.add(Restrictions.between("seatingCapacity", from, to));
        } else {
            if (from != null) {
                criteria.add(Restrictions.ge("seatingCapacity", from));
            } else if (to != null) {
                criteria.add(Restrictions.le("seatingCapacity", to));
            }
        }
    }

    private void addCriteriaForCarryingCapacityKg(Criteria criteria,
                                                  Integer from, Integer to) {
        if (from != null && to != null) {
            criteria.add(Restrictions.between("carryingCapacityKg", from, to));
        } else {
            if (from != null) {
                criteria.add(Restrictions.ge("carryingCapacityKg", from));
            } else if (to != null) {
                criteria.add(Restrictions.le("carryingCapacityKg", to));
            }
        }
    }

    private void addCriteriaForFlightRangeKm(Criteria criteria,
                                             Integer from, Integer to) {
        if (from != null && to != null) {
            criteria.add(Restrictions.between("flightRangeKm", from, to));
        } else {
            if (from != null) {criteria.add(Restrictions.ge("flightRangeKm", from));
            } else if (to != null) {
                criteria.add(Restrictions.le("flightRangeKm", to));
            }
        }
    }

    private void addCriteriaForFuelConsumptionLitersPerHour(Criteria criteria,
                                                       Integer from, Integer to) {
        if (from != null && to != null) {
            criteria.add(Restrictions.between(
                    "fuelConsumptionLitersPerHour", from, to));
        } else {
            if (from != null) {
                criteria.add(Restrictions.ge("fuelConsumptionLitersPerHour", from));
            } else if (to != null) {
                criteria.add(Restrictions.le("fuelConsumptionLitersPerHour", to));
            }
        }
    }
}
