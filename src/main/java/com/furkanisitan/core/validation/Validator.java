package com.furkanisitan.core.validation;

import com.furkanisitan.core.exceptions.CreateInstanceException;
import com.furkanisitan.core.exceptions.ForeignKeyConstraintException;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.core.model.Entity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Pair;

import java.io.Serializable;
import java.lang.reflect.Field;

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
     * @param field property path.
     * @param value the query value.
     * @param <V>   the type of value.
     * @return the {@link T} entity.
     * @throws RecordNotFoundException if entity not exists.
     */
    public <V> T findBy(String field, V value) {
        return repository.findOne(getExample(field, value))
                .orElseThrow(() -> new RecordNotFoundException(clazz.getSimpleName(), Pair.of(field, value)));
    }

    /**
     * @param field property path.
     * @param value the query value.
     * @param <V>   the type of value.
     * @return the {@link T} entity.
     * @throws RecordNotFoundException if entity not exists.
     */
    public <V> T findForeignBy(String field, V value) {
        return repository.findOne(getExample(field, value))
                .orElseThrow(() -> new ForeignKeyConstraintException(field, value));
    }

    /**
     * Checks an entity with the {@literal field} exists.
     *
     * @param field property path.
     * @param value the query value.
     * @param <V>   the type of value.
     * @throws RecordNotFoundException if entity not exists.
     */
    public <V> void existsBy(String field, V value) {
        if (!repository.exists(getExample(field, value)))
            throw new RecordNotFoundException(clazz.getSimpleName(), Pair.of(field, value));
    }

    private <V> Example<T> getExample(String field, V value) {
        try {
            var probe = clazz.getDeclaredConstructor().newInstance();

            var declaredField = getField(field);
            declaredField.setAccessible(true);
            declaredField.set(probe, value);

            var exampleMatcher = ExampleMatcher.matchingAny().withMatcher(field, ExampleMatcher.GenericPropertyMatchers.exact());

            return Example.of(probe, exampleMatcher);
        } catch (Exception e) {
            throw new CreateInstanceException(e.getMessage());
        }
    }

    private Field getField(String field) throws NoSuchFieldException {
        try {
            return clazz.getDeclaredField(field);
        } catch (NoSuchFieldException e) {
            return clazz.getSuperclass().getDeclaredField(field);
        }
    }
}
