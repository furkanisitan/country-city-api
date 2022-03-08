package com.furkanisitan.core.exceptions;

import lombok.Getter;

/**
 * An exception representing a unique constraint violation.
 */

@Getter
public class UniqueConstraintException extends RuntimeException {

    private final String name;
    private final Object value;

    /**
     * Creates a new UniqueConstraintException.
     *
     * @param name  the name of the unique field.
     * @param value the rejected value.
     */
    public UniqueConstraintException(String name, Object value) {
        super(String.format("%s: must be unique. {rejectedValue: %s}", name, value));
        this.name = name;
        this.value = value;
    }
}
