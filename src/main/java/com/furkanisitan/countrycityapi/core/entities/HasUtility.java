package com.furkanisitan.countrycityapi.core.entities;

/**
 * @param <T> the type of the {@link EntityUtility} class.
 */
public interface HasUtility<T extends EntityUtility<Entity<?>>> {

    T utility();
}
