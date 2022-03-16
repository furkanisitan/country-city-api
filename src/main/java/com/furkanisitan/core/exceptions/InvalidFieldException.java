package com.furkanisitan.core.exceptions;

import lombok.Getter;

/**
 * An exception representing that the class doesn't have a field of a specified name.
 * It is used for errors caused by external parameters.
 */

@Getter
public class InvalidFieldException extends RuntimeException {

    private final String name;

    /**
     * Creates a new InvalidFieldException.
     *
     * @param name the field name.
     */
    public InvalidFieldException(String name) {
        super(String.format("%s: field must be valid.", name));
        this.name = name;
    }
}
