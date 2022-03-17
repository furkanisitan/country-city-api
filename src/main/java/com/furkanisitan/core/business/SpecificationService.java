package com.furkanisitan.core.business;

import com.furkanisitan.core.criteria.FilterCriteria;
import com.furkanisitan.core.criteria.RequestCriteria;
import com.furkanisitan.core.model.Entity;

import java.io.Serializable;
import java.util.List;

public interface SpecificationService<T extends Entity<ID>, ID extends Serializable> extends Service<T, ID> {

    /**
     * Returns all instances of the {@link T} by {@literal criteria}.
     *
     * @param criteria contains filtering, sorting and pagination parameters.
     * @return all instances of the {@link T} by {@literal criteria}.
     */
    List<T> all(RequestCriteria criteria);

    /**
     * Returns all instances of the {@link T} by {@literal criteria}.
     *
     * @param criteria contains filtering parameters.
     * @return all instances of the {@link T} by {@literal criteria}.
     */
    List<T> all(List<FilterCriteria> criteria);

}
