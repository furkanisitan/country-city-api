package com.furkanisitan.core.api;

import com.furkanisitan.core.exceptions.RouteBodyMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

public interface ApiHelpers {

    /**
     * Validates that the {@literal routeValue} and {@literal propertyValue} are equal.
     *
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
     * Creates a {@link URI} from {@literal info}.
     *
     * @param info either the value returned from a "mock" controller invocation or the "mock" controller itself after an invocation.
     * @return a {@link URI}.
     * @see MvcUriComponentsBuilder#on(Class)
     * @see MvcUriComponentsBuilder#controller(Class)
     */
    static URI getUri(Object info) {
        return MvcUriComponentsBuilder.fromMethodCall(info).buildAndExpand().toUri();
    }

}
