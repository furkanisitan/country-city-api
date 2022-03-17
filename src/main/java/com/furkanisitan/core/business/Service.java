package com.furkanisitan.core.business;

import com.furkanisitan.core.criteria.PageCriteria;
import com.furkanisitan.core.model.Entity;

import java.io.Serializable;
import java.util.List;

public interface Service<T extends Entity<ID>, ID extends Serializable> {

    /**
     * Returns all instances of the {@link T} by {@literal criteria}.
     *
     * @param criteria contains sorting and pagination parameters.
     * @return all instances of the {@link T} by {@literal criteria}.
     */
    List<T> all(PageCriteria criteria);

    /**
     * Checks an entity with the {@literal field} exists.
     *
     * @param name  the field name.
     * @param value the query value.
     * @param <V>   the type of value.
     * @return {@code true} if an entity with the given field exists, {@code false} otherwise.
     */
    <V> boolean existsBy(String name, V value);

}
