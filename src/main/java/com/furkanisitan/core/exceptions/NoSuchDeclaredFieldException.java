package com.furkanisitan.core.exceptions;

import lombok.Getter;

/**
 * An exception representing that the class doesn't have a field of a specified name.
 */

@Getter
public class NoSuchDeclaredFieldException extends RuntimeException {

    private final String name;

    /**
     * Creates a new NoSuchDeclaredFieldException.
     *
     * @param name the field name.
     */
    public NoSuchDeclaredFieldException(String name) {
        super(String.format("%s: No such field.", name));
        this.name = name;
    }

}
