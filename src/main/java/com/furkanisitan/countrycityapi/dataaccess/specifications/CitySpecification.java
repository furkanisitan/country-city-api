package com.furkanisitan.countrycityapi.dataaccess.specifications;

import com.furkanisitan.core.criteria.AbstractSpecification;
import com.furkanisitan.core.criteria.FilterCriteria;
import com.furkanisitan.countrycityapi.model.entities.City;

public final class CitySpecification extends AbstractSpecification<City> {

    public CitySpecification(FilterCriteria criteria) {
        super(criteria);
    }
}
