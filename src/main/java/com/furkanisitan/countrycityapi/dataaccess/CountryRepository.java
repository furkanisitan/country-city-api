package com.furkanisitan.countrycityapi.dataaccess;

import com.furkanisitan.core.dataaccess.SpecificationRepository;
import com.furkanisitan.countrycityapi.model.entities.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends SpecificationRepository<Country, Long> {
}
