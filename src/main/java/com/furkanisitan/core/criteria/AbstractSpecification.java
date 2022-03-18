package com.furkanisitan.core.criteria;

import com.furkanisitan.core.exceptions.InvalidValueException;
import com.furkanisitan.core.exceptions.UnsupportedFilterOperatorException;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * An abstract base class that implements the {@link Specification}.
 *
 * @param <T> the type of the Root the resulting Specification operates on.
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public abstract class AbstractSpecification<T> implements Specification<T> {

    private final FilterCriteria criteria;

    protected AbstractSpecification(FilterCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        try {

            var value = getParsedValue();
            var fieldName = criteria.getField().getName();
            var path = root.get(fieldName);

            return switch (criteria.getOperator()) {
                case EQUALS -> criteriaBuilder.equal(root.get(fieldName), value);
                case NOT_EQUALS -> criteriaBuilder.notEqual(root.get(fieldName), value);
                case STARTS_WITH -> like(criteriaBuilder, root.get(fieldName), value + "%");
                case ENDS_WITH -> like(criteriaBuilder, root.get(fieldName), "%" + value);
                case CONTAINS -> like(criteriaBuilder, root.get(fieldName), "%" + value + "%");
                case GREATER_THAN -> criteriaBuilder.greaterThan(root.get(fieldName), value);
                case LESS_THAN -> criteriaBuilder.lessThan(root.get(fieldName), value);
                case GREATER_THAN_OR_EQUAL -> criteriaBuilder.greaterThanOrEqualTo(root.get(fieldName), value);
                case LESS_THAN_OR_EQUAL -> criteriaBuilder.lessThanOrEqualTo(root.get(fieldName), value);
            };

        } catch (NumberFormatException | DateTimeParseException e) {
            throw new InvalidValueException(criteria.getField().getName(), criteria.getValue());
        }

    }

    private Predicate like(CriteriaBuilder criteriaBuilder, Expression<String> var1, String var2) {
        if (!criteria.getField().getType().isAssignableFrom(String.class))
            throw new UnsupportedFilterOperatorException(criteria.getField().getName(), criteria.getOperator());

        return criteriaBuilder.like(var1, var2);
    }

    private Comparable getParsedValue() {

        Class<?> type = criteria.getField().getType();
        var value = criteria.getValue();

        if (type.isEnum()) return getParsedEnum(type, value);
        if (type.isAssignableFrom(Instant.class)) return Instant.parse(value);
        if (type.isAssignableFrom(LocalDate.class)) return LocalDate.parse(value);
        if (type.isAssignableFrom(LocalDateTime.class)) return LocalDateTime.parse(value);
        if (type.isAssignableFrom(LocalTime.class)) return LocalTime.parse(value);
        return value;
    }

    private Enum<?> getParsedEnum(Class type, String value) {
        try {
            return Enum.valueOf(type, value);
        } catch (IllegalArgumentException e) {
            throw new InvalidValueException(criteria.getField().getName(), criteria.getValue());
        }
    }

}
