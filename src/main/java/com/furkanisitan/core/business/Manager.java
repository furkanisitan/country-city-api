package com.furkanisitan.core.business;

import com.furkanisitan.core.criteria.PageCriteria;
import com.furkanisitan.core.model.Entity;
import com.furkanisitan.core.utils.ExampleUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

public abstract class Manager<T extends Entity<ID>, ID extends Serializable> implements Service<T, ID> {

    protected final Class<T> clazz;
    private final JpaRepository<T, ID> repository;

    protected Manager(Class<T> clazz, JpaRepository<T, ID> repository) {
        this.clazz = clazz;
        this.repository = repository;
    }

    /**
     * Returns all instances of the {@link T} by {@literal criteria}.
     *
     * @param criteria contains sorting and pagination parameters.
     * @return all instances of the {@link T} by {@literal criteria}.
     */
    protected List<T> all(PageCriteria criteria) {

        if (criteria.getPageable() == null && criteria.getSort() == null)
            return repository.findAll();

        if (criteria.getPageable() != null)
            return repository.findAll(criteria.getPageable()).getContent();

        return repository.findAll(criteria.getSort());
    }

    @Override
    public <V> boolean existsBy(String name, V value) {
        return repository.exists(ExampleUtils.getExample(clazz, name, value));
    }

}
