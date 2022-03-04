package com.furkanisitan.core.exceptions;

/**
 * An exception representing a mismatch between the route parameter and the body property.
 */
public class RouteBodyMismatchException extends RuntimeException {

    /**
     * Creates a new RouteBodyMismatchException.
     *
     * @param routeName The name of the value in the route.
     */
    public RouteBodyMismatchException(String routeName) {
        this(routeName, routeName);
    }

    /**
     * Creates a new RouteBodyMismatchException.
     *
     * @param routeName    The name of the value in the route.
     * @param propertyName The name of the value in the body.
     */
    public RouteBodyMismatchException(String routeName, String propertyName) {
        super(String.format("The %s in the route does not match the %s in the body.", routeName, propertyName));
    }
}
