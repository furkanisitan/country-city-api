package com.furkanisitan.countrycityapi.core.exceptions;

import lombok.Getter;

@Getter
public class UniqueConstraintViolationException extends RuntimeException {

    private final String name;
    private final Object value;

    /**
     * @param name  the name of the unique field.
     * @param value the rejected value.
     */
    public UniqueConstraintViolationException(String name, Object value) {
        super(String.format("'%s' must be unique. {rejectedValue: %s}", name, value));
        this.name = name;
        this.value = value;
    }
}