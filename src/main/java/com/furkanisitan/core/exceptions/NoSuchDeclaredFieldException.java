package com.furkanisitan.core.exceptions;

/**
 * An exception representing that the class doesn't have a field of a specified name.
 */
public class NoSuchDeclaredFieldException extends RuntimeException {

    public NoSuchDeclaredFieldException(String message) {
        super(String.format("NoSuchDeclaredFieldException: %s", message));
    }

}
