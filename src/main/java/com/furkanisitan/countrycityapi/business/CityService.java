package com.furkanisitan.countrycityapi.business;

import com.furkanisitan.core.business.Service;
import com.furkanisitan.core.exceptions.ForeignKeyConstraintException;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CityUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CityListResponse;
import com.furkanisitan.countrycityapi.model.responses.CityResponse;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.List;

@Validated
public interface CityService extends Service {

    /**
     * Returns all cities by mapping them to {@link CityResponse}.
     *
     * @return all cities by mapping them to {@link CityResponse}.
     */
    List<CityListResponse> findAll();

    /**
     * Returns all cities of the country by mapping them to {@link CityResponse}.
     *
     * @return all cities of the country by mapping them to {@link CityResponse}.
     */
    List<CityListResponse> findAllByCountryId(Long countryId);

    /**
     * Returns a {@link CityResponse} by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @return a {@link CityResponse} by {@literal id}.
     * @implSpec return {@code null} if entity not exists by {@literal id}.
     */
    @Nullable
    CityResponse findById(Long id);

    /**
     * Creates a new city.
     *
     * @param request the dto object required to create a new city.
     * @return the added city by mapping it to {@link CityResponse}.
     * @throws ForeignKeyConstraintException if country is not exists by {@link CityCreateRequest#getCountryCode()}.
     */
    CityResponse create(@Valid CityCreateRequest request);

    /**
     * Updates the city.
     *
     * @param request the dto object required to update the city.
     * @throws RecordNotFoundException       if city is not exists.
     * @throws ForeignKeyConstraintException if country is not exists by {@link CityUpdateRequest#getCountryCode()}.
     */
    void update(@Valid CityUpdateRequest request);

    /**
     * Deletes city by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @throws RecordNotFoundException if city is not exists by {@literal id}.
     */
    void deleteById(Long id);

}

