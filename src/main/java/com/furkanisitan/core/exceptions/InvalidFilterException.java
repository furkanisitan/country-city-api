package com.furkanisitan.core.exceptions;

import lombok.Getter;

/**
 * An exception representing that the specified filter is not a valid format.
 */

@Getter
public class InvalidFilterException extends RuntimeException {

    private final String filter;

    /**
     * Creates a new InvalidFilterException.
     *
     * @param filter the query parameter.
     */
    public InvalidFilterException(String filter) {
        super(String.format("%s: filter format must be like '{field}{operator}{value}'.", filter));
        this.filter = filter;
    }
}
