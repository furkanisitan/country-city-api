package com.furkanisitan.core.validation;

import com.furkanisitan.core.exceptions.CreateInstanceException;
import com.furkanisitan.core.exceptions.ForeignKeyConstraintException;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.core.exceptions.UniqueConstraintException;
import com.furkanisitan.core.model.Entity;
import com.furkanisitan.core.utils.GenericUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    /**
     * Checks if {@literal field} is unique.
     *
     * @param field property path.
     * @param value the query value.
     * @param <V>   the type of value.
     * @throws UniqueConstraintException if {@literal field} is not unique.
     */
    public <V> void uniqueBy(String field, V value) {
        if (repository.exists(getExample(field, value)))
            throw new UniqueConstraintException(field, value);
    }

    /**
     * Checks if {@literal field} is unique for update.
     *
     * @param field property path.
     * @param value the query value.
     * @param <V>   the type of value.
     * @throws UniqueConstraintException if {@literal field} is not unique for update.
     */
    public <V> void uniqueBy(String field, V value, ID id) {
        T entity = repository.findOne(getExample(field, value)).orElse(null);
        if (entity != null && !Objects.equals(entity.getId(), id))
            throw new UniqueConstraintException(field, value);
    }

    private <V> Example<T> getExample(String field, V value) {
        try {
            var probe = clazz.getDeclaredConstructor().newInstance();

            var declaredField = GenericUtils.getField(clazz, field);
            declaredField.set(probe, value);

            var exampleMatcher = ExampleMatcher.matchingAny().withMatcher(field, ExampleMatcher.GenericPropertyMatchers.exact());

            return Example.of(probe, exampleMatcher);
        } catch (Exception e) {
            throw new CreateInstanceException(e.getMessage());
        }
    }

}
