package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.countrycityapi.business.CityService;
import com.furkanisitan.countrycityapi.dataaccess.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class CityManager implements CityService {

    private final CityRepository repository;

    @Autowired
    public CityManager(CityRepository repository) {
        this.repository = repository;
    }
}
