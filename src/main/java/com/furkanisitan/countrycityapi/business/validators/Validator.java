package com.furkanisitan.countrycityapi.business.validators;

import com.furkanisitan.core.exceptions.RecordNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Pair;

abstract class Validator<T, ID> {

    private static final String ID_PRIMARY_KEY_NAME = "id";

    private final JpaRepository<T, ID> repository;
    private final String defaultName;

    /**
     * @param repository  the {@link JpaRepository} of entity.
     * @param defaultName the name of entity
     */
    Validator(JpaRepository<T, ID> repository, String defaultName) {
        this.repository = repository;
        this.defaultName = defaultName;
    }

    /**
     * Checks if {@literal id} is exists.
     *
     * @param id   the primary key of the entity.
     * @param name the name of primary key.
     * @return the {@link T} entity if exists by id.
     * @throws RecordNotFoundException if {@literal id} is not exists.
     */
    public T findIfIdIsExists(ID id, String name) {
        return repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(defaultName, Pair.of(name, id)));
    }

    /**
     * {@code name} defaults to {@value ID_PRIMARY_KEY_NAME}.
     *
     * @see #findIfIdIsExists(ID, String)
     */
    public T findIfIdIsExists(ID id) {
        return findIfIdIsExists(id, ID_PRIMARY_KEY_NAME);
    }

    /**
     * Checks if {@literal id} is exists.
     *
     * @param id   the primary key of the entity.
     * @param name the name of primary key.
     * @throws RecordNotFoundException if {@literal id} is not exists.
     */
    public void idIsExists(ID id, String name) {
        if (!repository.existsById(id))
            throw new RecordNotFoundException(defaultName, Pair.of(name, id));
    }

    /**
     * {@code name} defaults to {@value ID_PRIMARY_KEY_NAME}.
     *
     * @see #idIsExists(ID, String)
     */
    public void idIsExists(ID id) {
        idIsExists(id, ID_PRIMARY_KEY_NAME);
    }

}
