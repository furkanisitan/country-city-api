package com.furkanisitan.countrycityapi.business.validators;

import com.furkanisitan.core.validation.Validator;
import com.furkanisitan.countrycityapi.dataaccess.CountryRepository;
import com.furkanisitan.countrycityapi.model.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CountryValidator extends Validator<Country, Long> {

    @Autowired
    public CountryValidator(CountryRepository repository) {
        super(Country.class, repository);
    }

}
