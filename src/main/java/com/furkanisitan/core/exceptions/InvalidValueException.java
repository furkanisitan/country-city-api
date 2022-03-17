package com.furkanisitan.core.exceptions;

import lombok.Getter;

/**
 * An exception representing that the specified value is not valid for field.
 */

@Getter
public class InvalidValueException extends RuntimeException {

    private final String field;
    private final String value;

    /**
     * Creates a new InvalidValueException.
     *
     * @param field the field name.
     * @param value the value for query.
     */
    public InvalidValueException(String field, String value) {
        super(String.format("%s: value must be valid. {rejectedValue: %s}", field, value));
        this.field = field;
        this.value = value;
    }
}
