package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.countrycityapi.business.CountryService;
import com.furkanisitan.countrycityapi.dataaccess.CountryRepository;
import com.furkanisitan.countrycityapi.model.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@Primary
public class CountryManager implements CountryService {

    private final CountryRepository repository;

    @Autowired
    public CountryManager(CountryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Country getByCode(String code) {
        return repository.getByCode(code);
    }
}
