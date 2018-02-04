package ua.olezha.airline.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BetweenExamplesRepository<T> {

    List<T> findAll(T fromExampleEntity, T toExampleEntity);
}
