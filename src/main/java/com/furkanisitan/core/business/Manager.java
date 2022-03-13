package com.furkanisitan.core.business;

import com.furkanisitan.core.model.Entity;
import com.furkanisitan.core.utils.ExampleUtils;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public abstract class Manager<T extends Entity<ID>, ID extends Serializable> implements Service {

    private final Class<T> clazz;
    private final JpaRepository<T, ID> repository;

    protected Manager(Class<T> clazz, JpaRepository<T, ID> repository) {
        this.clazz = clazz;
        this.repository = repository;
    }

    @Override
    public <V> boolean existsBy(String name, V value) {
        return repository.exists(ExampleUtils.getExample(clazz, name, value));
    }
    
}
