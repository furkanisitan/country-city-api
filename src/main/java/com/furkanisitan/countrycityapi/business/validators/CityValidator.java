package com.furkanisitan.countrycityapi.business.validators;

import com.furkanisitan.countrycityapi.dataaccess.CityRepository;
import com.furkanisitan.countrycityapi.model.entities.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityValidator extends Validator<City, Long> {

    @Autowired
    public CityValidator(CityRepository repository) {
        super(repository, City.class.getSimpleName());
    }

}
