package com.furkanisitan.countrycityapi.business;

import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.core.exceptions.UniqueConstraintException;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CountryResponse;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import java.util.List;

public interface CountryService {

    /**
     * Returns all countries by mapping them to {@link CountryResponse}.
     *
     * @return all countries by mapping them to {@link CountryResponse}.
     */
    List<CountryResponse> findAll();

    /**
     * Returns a {@link CountryResponse} by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @return a {@link CountryResponse} by {@literal id}.
     * @implSpec return {@code null} if entity not exists by {@literal id}.
     */
    @Nullable
    CountryResponse findById(Long id);

    /**
     * Creates a new country.
     *
     * @param request the dto object required to create a new country.
     * @return the added city by mapping it to {@link CountryResponse}.
     * @throws UniqueConstraintException if country code is not unique.
     */
    CountryResponse create(@Valid CountryCreateRequest request);

    /**
     * Updates the country.
     *
     * @param request the dto object required to update the country.
     * @throws RecordNotFoundException if country is not exists.
     * @throws UniqueConstraintException if country code is not unique.
     */
    void update(@Valid CountryUpdateRequest request);

    /**
     * Deletes country by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @throws RecordNotFoundException if country is not exists by {@literal id}.
     */
    void deleteById(Long id);

}
