package com.furkanisitan.core.exceptions;

import lombok.Getter;

/**
 * An exception representing that the foreign key does not exist.
 */

@Getter
public class ForeignKeyConstraintException extends RuntimeException {

    private final String name;
    private final Object value;

    /**
     * Creates a new {@link ForeignKeyConstraintException}.
     *
     * @param name  the name of foreign key.
     * @param value the value of foreign key.
     */
    public ForeignKeyConstraintException(String name, Object value) {
        super(String.format("%s: the foreign key does not exist. {rejectedValue: %s}", name, value));
        this.name = name;
        this.value = value;
    }

}
