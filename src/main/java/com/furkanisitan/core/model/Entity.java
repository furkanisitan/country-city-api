package com.furkanisitan.core.model;

import java.io.Serializable;

/**
 * @param <ID> the type of the id of the entity.
 */
public interface Entity<ID extends Serializable> {

    ID getId();
}
