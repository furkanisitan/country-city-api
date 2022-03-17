package com.furkanisitan.countrycityapi.dataaccess.specifications;

import com.furkanisitan.core.criteria.AbstractSpecification;
import com.furkanisitan.core.criteria.FilterCriteria;
import com.furkanisitan.countrycityapi.model.entities.Language;

public final class LanguageSpecification extends AbstractSpecification<Language> {

    public LanguageSpecification(FilterCriteria criteria) {
        super(criteria);
    }
}
