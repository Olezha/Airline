package ua.olezha.airline.repository;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.olezha.airline.model.aircraft.Aircraft;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Repository
public class AircraftRepositoryImpl implements BetweenExamplesRepository<Aircraft> {

    private final SessionFactory sessionFactory;

    @Autowired
    public AircraftRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Override
    @Transactional
    public List<Aircraft> findAll(Aircraft fromExampleEntity, Aircraft toExampleEntity) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Aircraft");
        return query.list();
    }
}
