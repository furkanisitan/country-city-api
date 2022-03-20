package com.furkanisitan.core.exceptions;

import com.furkanisitan.core.criteria.FilterOperator;
import lombok.Getter;

/**
 * An exception representing that the specified text is not a filter operator.
 */

@Getter
public class InvalidFilterOperatorException extends RuntimeException {

    private final String operator;

    /**
     * Creates a new {@link InvalidFilterOperatorException}.
     *
     * @param operator the text for {@link FilterOperator}.
     */
    public InvalidFilterOperatorException(String operator) {
        super(String.format("%s: filter operator must be valid. {validValues: ':', '!:', ':%%', '%%:', '%%', '>', '<', '>:', '<:'}", operator));
        this.operator = operator;
    }
}
