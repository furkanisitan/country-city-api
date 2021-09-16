package com.furkanisitan.countrycityapi.core.entities;

/**
 * A base class for classes that will contain utility methods for the {@link Entity} classes.
 *
 * @param <T> the type of the entity to handle.
 */
public abstract class EntityUtility<T extends Entity<?>> {

    protected final T clazz;

    protected EntityUtility(T clazz) {
        this.clazz = clazz;
    }
}