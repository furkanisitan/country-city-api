package com.furkanisitan.core.model.utility;

import com.furkanisitan.core.model.BaseEntity;

/**
 * A base class for classes that will contain utility methods for the {@link BaseEntity} classes.
 *
 * @param <T> the type of the entity to handle.
 */
public abstract class EntityUtility<T extends BaseEntity<?>> {

    protected final T clazz;

    /**
     * @param clazz the instance of the entity to handle.
     */
    protected EntityUtility(T clazz) {
        this.clazz = clazz;
    }
}