package ua.olezha.airline.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import ua.olezha.airline.model.aircraft.Aircraft;

import javax.persistence.EntityManagerFactory;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class AircraftRepositoryImplTest {

    private BetweenExamplesRepository<Aircraft> betweenExamplesRepository;

    @Before
    public void setUp() {
        EntityManagerFactory entityManagerFactoryMock = mock(EntityManagerFactory.class);
        SessionFactory sessionFactoryMock = mock(SessionFactory.class);
        when(entityManagerFactoryMock.unwrap(SessionFactory.class)).thenReturn(sessionFactoryMock);
        Session sessionMock = mock(Session.class);
        when(sessionFactoryMock.getCurrentSession()).thenReturn(sessionMock);
        Criteria criteriaMock = mock(Criteria.class);
        when(sessionMock.createCriteria(Aircraft.class)).thenReturn(criteriaMock);
        betweenExamplesRepository = new AircraftRepositoryImpl(entityManagerFactoryMock);
    }

    @Test
    public void findAllTest() {
        assertNotNull(betweenExamplesRepository.findAll(mock(Aircraft.class), mock(Aircraft.class)));
    }
}
