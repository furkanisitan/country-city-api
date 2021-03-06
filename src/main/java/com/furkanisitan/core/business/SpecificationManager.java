package com.furkanisitan.core.business;

import com.furkanisitan.core.criteria.AbstractSpecification;
import com.furkanisitan.core.criteria.FilterCriteria;
import com.furkanisitan.core.criteria.RequestCriteria;
import com.furkanisitan.core.dataaccess.SpecificationRepository;
import com.furkanisitan.core.model.Entity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class SpecificationManager<T extends Entity<ID>, ID extends Serializable> extends Manager<T, ID> implements SpecificationService<T, ID> {

    private final SpecificationRepository<T, ID> repository;

    protected SpecificationManager(Class<T> clazz, SpecificationRepository<T, ID> repository) {
        super(clazz, repository);
        this.repository = repository;
    }

    /**
     * Returns all instances of the {@link T} by {@literal criteria}.
     *
     * @param criteria contains filtering, sorting and pagination parameters.
     * @return all instances of the {@link T} by {@literal criteria}.
     */
    protected List<T> all(RequestCriteria criteria) {

        var specification = getSpecification(criteria.getFilterCriteria());

        if (criteria.getPageCriteria().getPageable() != null)
            return repository.findAll(specification, criteria.getPageCriteria().getPageable()).getContent();

        return repository.findAll(specification, criteria.getPageCriteria().getSort());

    }

    /**
     * Returns all instances of the {@link T} by {@literal criteria}.
     *
     * @param criteria contains filtering parameters.
     * @return all instances of the {@link T} by {@literal criteria}.
     */
    protected List<T> all(List<FilterCriteria> criteria) {
        return repository.findAll(getSpecification(criteria));
    }

    private Specification<T> getSpecification(List<FilterCriteria> criteria) {

        if (CollectionUtils.isEmpty(criteria))
            return null;

        Specification<T> specification = Specification.where(getSpecification(criteria.get(0)));
        for (int i = 1; i < criteria.size(); i++) {
            specification.and(getSpecification(criteria.get(i)));
        }
        return specification;
    }

    private Specification<T> getSpecification(FilterCriteria criteria) {

        if (criteria == null)
            return null;

        return new AbstractSpecification<>(criteria) {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return super.toPredicate(root, query, criteriaBuilder);
            }
        };
    }
}
