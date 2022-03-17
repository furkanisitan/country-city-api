package com.furkanisitan.core.exceptions;

import com.furkanisitan.core.criteria.FilterOperator;
import lombok.Getter;

/**
 * An exception representing that the specified operator is not supported for field.
 */

@Getter
public class UnsupportedFilterOperatorException extends RuntimeException {

    private final String field;
    private final FilterOperator operator;

    /**
     * Creates a new UnsupportedFilterOperatorException.
     *
     * @param field    the field name.
     * @param operator the filter operator.
     */
    public UnsupportedFilterOperatorException(String field, FilterOperator operator) {
        super(String.format("%s: the '%s' operator is not supported for this field.", field, operator.getText()));
        this.field = field;
        this.operator = operator;
    }
}
