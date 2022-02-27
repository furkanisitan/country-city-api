package com.furkanisitan.countrycityapi.business.validators;

import com.furkanisitan.core.exceptions.ForeignKeyConstraintException;
import com.furkanisitan.countrycityapi.dataaccess.LanguageRepository;
import com.furkanisitan.countrycityapi.model.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageValidator {

    private static final String ID_FOREIGN_KEY_NAME = "languageId";

    private final LanguageRepository repository;

    @Autowired
    public LanguageValidator(LanguageRepository repository) {
        this.repository = repository;
    }

    /**
     * Checks if {@literal id} is exists.
     *
     * @param id   the primary key of the entity.
     * @param name the name of primary key.
     * @return a proxy object of {@link Language} if exists by id.
     * @throws ForeignKeyConstraintException if {@literal id} is not exists.
     */
    public Language getIfIdForeignKeyIsExists(Long id, String name) {
        return repository.getLanguageById(id).orElseThrow(() -> new ForeignKeyConstraintException(name, id));
    }

    /**
     * {@code name} defaults to {@value ID_FOREIGN_KEY_NAME}.
     *
     * @see #getIfIdForeignKeyIsExists(Long, String)
     */
    public Language getIfIdForeignKeyIsExists(Long id) {
        return getIfIdForeignKeyIsExists(id, ID_FOREIGN_KEY_NAME);
    }
}
