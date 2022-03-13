package com.furkanisitan.core.exceptions;

/**
 * An exception representing that an error occurred while creating an instance using reflection.
 */
public class CreateInstanceException extends RuntimeException {

    public CreateInstanceException(String message) {
        super(String.format("CreateInstanceException: %s", message));
    }
}
