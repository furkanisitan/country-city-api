package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.countrycityapi.business.CountryService;
import com.furkanisitan.countrycityapi.dataaccess.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class CountryManager implements CountryService {

    private final CountryRepository repository;

    @Autowired
    public CountryManager(CountryRepository repository) {
        this.repository = repository;
    }
}
