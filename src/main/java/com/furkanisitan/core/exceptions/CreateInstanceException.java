package com.furkanisitan.core.exceptions;

/**
 * An exception representing that an error occurred while creating an instance using reflection.
 */
public class CreateInstanceException extends RuntimeException {

    /**
     * Creates a new {@link CreateInstanceException}.
     *
     * @param message the exception message.
     */
    public CreateInstanceException(String message) {
        super(String.format("CreateInstanceException: %s", message));
    }
}
