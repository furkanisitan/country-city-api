package com.furkanisitan.core.exceptions;

import lombok.Getter;
import org.springframework.data.util.Pair;

/**
 * An exception representing that the foreign key does not exist.
 */

@Getter
public class RecordNotFoundException extends RuntimeException {

    private final String name;
    private final Pair<String, Object>[] parameters;

    /**
     * @param name       the name of record.
     * @param parameters the names and values of the parameters used in the query.
     */
    @SafeVarargs
    public RecordNotFoundException(String name, Pair<String, Object>... parameters) {
        super(Helpers.notFound(name, parameters));
        this.name = name;
        this.parameters = parameters;
    }

}
