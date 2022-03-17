package com.furkanisitan.countrycityapi.dataaccess;

import com.furkanisitan.core.dataaccess.SpecificationRepository;
import com.furkanisitan.countrycityapi.model.entities.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends SpecificationRepository<City, Long> {

    List<City> findAllByCountryId(long countryId);

}

