package com.furkanisitan.countrycityapi.business;

import com.furkanisitan.countrycityapi.business.dtos.city.CityCreateDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityUpdateDto;
import com.furkanisitan.countrycityapi.core.exceptions.EntityNotExistsException;
import com.furkanisitan.countrycityapi.core.exceptions.ForeignKeyConstraintViolationException;

import java.util.List;

public interface CityService {

    /**
     * Returns all cities by mapping them to {@link CityDto}.
     *
     * @return all cities by mapping them to {@link CityDto}.
     */
    List<CityDto> findAll();

    /**
     * Returns a {@link CityDto} by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @return a {@link CityDto} by {@literal id}.
     */
    CityDto findById(Long id);

    /**
     * Creates a new city.
     *
     * @param cityCreateDto the dto object required to create a new city.
     * @return the added city by mapping it to {@link CityDto}.
     * @throws ForeignKeyConstraintViolationException if country is not exists by {@link CityCreateDto#getCountryCode()}.
     */
    CityDto create(CityCreateDto cityCreateDto);

    /**
     * Updates the city.
     *
     * @param cityUpdateDto the dto object required to update the city.
     * @throws EntityNotExistsException               if city is not exists.
     * @throws ForeignKeyConstraintViolationException if country is not exists by {@link CityUpdateDto#getCountryCode()}.
     */
    void update(CityUpdateDto cityUpdateDto);

    /**
     * Deletes city by {@literal id}.
     *
     * @param id the primary key of the entity.
     * @throws EntityNotExistsException if city is not exists by {@literal id}.
     */
    void deleteById(Long id);

}

