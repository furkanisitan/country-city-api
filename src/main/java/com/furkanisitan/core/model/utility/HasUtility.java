package com.furkanisitan.core.model.utility;

/**
 * @param <T> the type of the {@link EntityUtility} class.
 */
public interface HasUtility<T extends EntityUtility<?>> {

    /**
     * @return an instance of a class of type {@link T} that contains helper methods for entity.
     */
    T utility();
}
