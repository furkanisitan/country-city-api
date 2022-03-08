package com.furkanisitan.core.api;

import com.furkanisitan.core.exceptions.RouteBodyMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

public interface ApiHelpers {

    /**
     * @param routeValue    The route value.
     * @param propertyValue The property value in the request body.
     * @param routeName     The name of the value in the route.
     * @param propertyName  The name of the value in the body.
     * @throws RouteBodyMismatchException if {@literal routeValue} and {@literal propertyValue} are not equal.
     */
    static void validateMismatch(Long routeValue, Long propertyValue, String routeName, String propertyName) {
        if (!Objects.equals(routeValue, propertyValue))
            throw new RouteBodyMismatchException(routeName, propertyName);
    }

    /**
     * {@code propertyName} defaults to {@literal  routeName}.
     *
     * @see #validateMismatch(Long, Long, String, String)
     */
    static void validateMismatch(Long routeValue, Long propertyValue, String routeName) {
        validateMismatch(routeValue, propertyValue, routeName, routeName);
    }

    /**
     * Creates a URI from {@literal info}.
     *
     * @param info Either the value returned from a "mock" controller invocation or the "mock" controller itself after an invocation.
     * @return a {@link URI}.
     * @see MvcUriComponentsBuilder#on(Class)
     * @see MvcUriComponentsBuilder#controller(Class)
     */
    static URI getUri(Object info) {
        return MvcUriComponentsBuilder.fromMethodCall(info).buildAndExpand().toUri();
    }

}
