package com.furkanisitan.countrycityapi.core.exceptions;

import com.furkanisitan.countrycityapi.core.utils.ApiErrors;
import lombok.Getter;
import org.springframework.data.util.Pair;

@Getter
public class EntityNotExistsException extends RuntimeException {

    private final String name;
    private final Pair<String, Object>[] parameters;

    /**
     * @param name       the name of entity.
     * @param parameters the names and values of the parameters used in the query.
     */
    @SafeVarargs
    public EntityNotExistsException(String name, Pair<String, Object>... parameters) {
        super(ApiErrors.notFound(name, parameters));
        this.name = name;
        this.parameters = parameters;
    }
}