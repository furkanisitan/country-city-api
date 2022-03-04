package com.furkanisitan.countrycityapi.business.validators;

import com.furkanisitan.core.exceptions.ForeignKeyConstraintException;
import com.furkanisitan.core.exceptions.UniqueConstraintException;
import com.furkanisitan.countrycityapi.dataaccess.CountryRepository;
import com.furkanisitan.countrycityapi.model.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CountryValidator extends Validator<Country, Long> {

    private static final String CODE_FOREIGN_KEY_NAME = "countryCode";
    private static final String CODE_UNIQUE_KEY_NAME = "code";

    private final CountryRepository repository;

    @Autowired
    public CountryValidator(CountryRepository repository) {
        super(repository, Country.class.getSimpleName());
        this.repository = repository;
    }

    /**
     * Checks if {@literal code} is exists.
     *
     * @param code the foreign key of country.
     * @param name the name of foreign key.
     * @return a proxy object of {@link Country} if exists by code.
     * @throws ForeignKeyConstraintException if {@literal code} is not exists.
     */
    public Country getIfCodeForeignKeyIsExists(String code, String name) {
        return repository.getCountryByCode(code).orElseThrow(() -> new ForeignKeyConstraintException(name, code));
    }

    /**
     * {@code name} defaults to {@value CODE_FOREIGN_KEY_NAME}.
     *
     * @see #getIfCodeForeignKeyIsExists(String, String)
     */
    public Country getIfCodeForeignKeyIsExists(String code) {
        return getIfCodeForeignKeyIsExists(code, CODE_FOREIGN_KEY_NAME);
    }

    /**
     * Checks if {@literal code} is unique.
     *
     * @param code unique value to validate.
     * @param name the name of unique field.
     * @throws UniqueConstraintException if {@literal code} is not unique.
     */
    public void codeIsUnique(String code, String name) {

        if (repository.existsByCode(code))
            throw new UniqueConstraintException(name, code);
    }

    /**
     * {@code name} defaults to {@value CODE_UNIQUE_KEY_NAME}.
     *
     * @see #codeIsUnique(String, String)
     */
    public void codeIsUnique(String code) {
        codeIsUnique(code, CODE_UNIQUE_KEY_NAME);
    }

    /**
     * Checks if {@literal code} is unique for update operation.
     *
     * @param code unique value to validate.
     * @param id   primary key of the course to be updated.
     * @param name the name of unique field.
     * @throws UniqueConstraintException if {@literal code} is not unique for update.
     */
    public void codeIsUnique(String code, Long id, String name) {

        Country country = repository.getCountryByCode(code).orElse(null);

        if (country != null && !Objects.equals(country.getId(), id))
            throw new UniqueConstraintException(name, code);
    }

    /**
     * {@code name} defaults to {@value CODE_UNIQUE_KEY_NAME}.
     *
     * @see #codeIsUnique(String, Long, String)
     */
    public void codeIsUnique(String code, Long id) {
        codeIsUnique(code, id, CODE_UNIQUE_KEY_NAME);
    }

}
