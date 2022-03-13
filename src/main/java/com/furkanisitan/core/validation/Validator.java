package com.furkanisitan.core.validation;

import com.furkanisitan.core.exceptions.ForeignKeyConstraintException;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.core.exceptions.UniqueConstraintException;
import com.furkanisitan.core.model.Entity;
import com.furkanisitan.core.utils.ExampleUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Pair;

import java.io.Serializable;
import java.util.Objects;

/**
 * @param <T>  the entity type to validate.
 * @param <ID> the type of the id of the entity.
 */
public abstract class Validator<T extends Entity<ID>, ID extends Serializable> {

    private final Class<T> clazz;
    private final JpaRepository<T, ID> repository;

    protected Validator(Class<T> clazz, JpaRepository<T, ID> repository) {
        this.clazz = clazz;
        this.repository = repository;
    }

    /**
     * @param name  the field name.
     * @param value the query value.
     * @param <V>   the type of value.
     * @return the {@link T} entity.
     * @throws RecordNotFoundException if entity not exists.
     */
    public <V> T findBy(String name, V value) {
        return repository.findOne(ExampleUtils.getExample(clazz, name, value))
                .orElseThrow(() -> new RecordNotFoundException(clazz.getSimpleName(), Pair.of(name, value)));
    }

    /**
     * @param name  the field name.
     * @param value the query value.
     * @param <V>   the type of value.
     * @return the {@link T} entity.
     * @throws RecordNotFoundException if entity not exists.
     */
    public <V> T findForeignBy(String name, V value) {
        return repository.findOne(ExampleUtils.getExample(clazz, name, value))
                .orElseThrow(() -> new ForeignKeyConstraintException(name, value));
    }

    /**
     * Checks an entity with the {@literal field} exists.
     *
     * @param name  the field name.
     * @param value the query value.
     * @param <V>   the type of value.
     * @throws RecordNotFoundException if entity not exists.
     */
    public <V> void existsBy(String name, V value) {
        if (!repository.exists(ExampleUtils.getExample(clazz, name, value)))
            throw new RecordNotFoundException(clazz.getSimpleName(), Pair.of(name, value));
    }

    /**
     * Checks if {@literal field} is unique.
     *
     * @param name  the field name.
     * @param value the query value.
     * @param <V>   the type of value.
     * @throws UniqueConstraintException if {@literal field} is not unique.
     */
    public <V> void uniqueBy(String name, V value) {
        if (repository.exists(ExampleUtils.getExample(clazz, name, value)))
            throw new UniqueConstraintException(name, value);
    }

    /**
     * Checks if {@literal field} is unique for update.
     *
     * @param name  the field name.
     * @param value the query value.
     * @param <V>   the type of value.
     * @throws UniqueConstraintException if {@literal field} is not unique for update.
     */
    public <V> void uniqueBy(String name, V value, ID id) {
        T entity = repository.findOne(ExampleUtils.getExample(clazz, name, value)).orElse(null);
        if (entity != null && !Objects.equals(entity.getId(), id))
            throw new UniqueConstraintException(name, value);
    }

}
