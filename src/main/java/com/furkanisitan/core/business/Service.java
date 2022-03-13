package com.furkanisitan.core.business;

public interface Service {

    /**
     * Checks an entity with the {@literal field} exists.
     *
     * @param name  the field name.
     * @param value the query value.
     * @param <V>   the type of value.
     * @return {@code true} if an entity with the given field exists, {@code false} otherwise.
     */
    <V> boolean existsBy(String name, V value);

}
