package com.furkanisitan.countrycityapi.dataaccess.specifications;

import com.furkanisitan.core.criteria.AbstractSpecification;
import com.furkanisitan.core.criteria.FilterCriteria;
import com.furkanisitan.countrycityapi.model.entities.Country;

public final class CountrySpecification extends AbstractSpecification<Country> {

    public CountrySpecification(FilterCriteria criteria) {
        super(criteria);
    }
}
