package com.furkanisitan.core.exceptions;

import org.springframework.data.util.Pair;

/**
 * This interface contains helper methods for {@link com.furkanisitan.core.exceptions} package.
 */
interface Helpers {

    /**
     * Creates an error message for not found.
     *
     * @param name       the name of the not found record.
     * @param parameters query parameters key-value pairs.
     * @return a message stating that the record not found.
     * @implNote example output could be like this: Record not found for parameters {id:'1'}.
     */
    @SafeVarargs
    static String notFound(String name, Pair<String, Object>... parameters) {

        StringBuilder builder = new StringBuilder(name + " not found");

        if (parameters.length > 0)
            builder.append(" for parameters");

        for (Pair<String, Object> pair : parameters)
            builder.append(String.format(" {%s='%s'}", pair.getFirst(), pair.getSecond()));

        return builder.append('.').toString();
    }

}
