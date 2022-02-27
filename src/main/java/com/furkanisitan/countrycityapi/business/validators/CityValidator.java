package com.furkanisitan.countrycityapi.business.validators;

import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.countrycityapi.dataaccess.CityRepository;
import com.furkanisitan.countrycityapi.model.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

@Component
public class CityValidator {

    private static final String ID_PRIMARY_KEY_NAME = "id";

    private final CityRepository repository;

    @Autowired
    public CityValidator(CityRepository repository) {
        this.repository = repository;
    }

    /**
     * Checks if {@literal id} is exists.
     *
     * @param id   the primary key of the entity.
     * @param name the name of primary key.
     * @return the {@link City} entity if exists by id.
     * @throws RecordNotFoundException if {@literal id} is not exists.
     */
    public City findIfIdIsExists(Long id, String name) {
        return repository.findById(id).orElseThrow(() ->
                new RecordNotFoundException(City.class.getSimpleName(), Pair.of(name, id)));
    }

    /**
     * {@code name} defaults to {@value ID_PRIMARY_KEY_NAME}.
     *
     * @see #findIfIdIsExists(Long, String)
     */
    public City findIfIdIsExists(Long id) {
        return findIfIdIsExists(id, ID_PRIMARY_KEY_NAME);
    }

    /**
     * Checks if {@literal id} is exists.
     *
     * @param id   the primary key of the entity.
     * @param name the name of primary key.
     * @throws RecordNotFoundException if {@literal id} is not exists.
     */
    public void idIsExists(Long id, String name) {
        if (!repository.existsById(id))
            throw new RecordNotFoundException(City.class.getSimpleName(), Pair.of(name, id));
    }

    /**
     * {@code name} defaults to {@value ID_PRIMARY_KEY_NAME}.
     *
     * @see #idIsExists(Long, String)
     */
    public void idIsExists(Long id) {
        idIsExists(id, ID_PRIMARY_KEY_NAME);
    }

}
