package com.furkanisitan.core.business;

import com.furkanisitan.core.model.Entity;

import java.io.Serializable;

public interface SpecificationService<T extends Entity<ID>, ID extends Serializable> extends Service<T, ID> {
}
