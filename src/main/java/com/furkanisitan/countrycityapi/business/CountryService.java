package com.furkanisitan.countrycityapi.business;

import com.furkanisitan.core.business.SpecificationService;
import com.furkanisitan.core.criteria.RequestCriteria;
import com.furkanisitan.core.exceptions.ForeignKeyConstraintException;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.core.exceptions.UniqueConstraintException;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryLanguageRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CountryCreateResponse;
import com.furkanisitan.countrycityapi.model.responses.CountryListResponse;
import com.furkanisitan.countrycityapi.model.responses.CountryResponse;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.List;

@Validated
public interface CountryService extends SpecificationService<Country, Long> {

    /**
     * Returns all countries by mapping them to {@link CountryListResponse}.
     *
     * @return all countries by mapping them to {@link CountryListResponse}.
     */
    List<CountryListResponse> findAll();

    /**
     * Returns all countries by mapping them to {@link CountryListResponse}.
     *
     * @return all countries by mapping them to {@link CountryListResponse}.
     */
    List<CountryListResponse> findAll(RequestCriteria criteria);

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
     * @return the added country by mapping it to {@link CountryCreateResponse}.
     * @throws UniqueConstraintException     if country code is not unique.
     * @throws ForeignKeyConstraintException if country is not exists by {@link CountryLanguageRequest#getLanguageId()}.
     */
    CountryCreateResponse create(@Valid CountryCreateRequest request);

    /**
     * Updates the country.
     *
     * @param request the dto object required to update the country.
     * @throws RecordNotFoundException       if country is not exists.
     * @throws UniqueConstraintException     if country code is not unique.
     * @throws ForeignKeyConstraintException if language is not exists by {@link CountryLanguageRequest#getLanguageId()}.
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
