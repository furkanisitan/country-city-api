package com.furkanisitan.countrycityapi.api.controllers;

import com.furkanisitan.countrycityapi.api.abstracts.CountriesApi;
import com.furkanisitan.countrycityapi.business.CountryService;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountriesController implements CountriesApi {

    private final CountryService countryService;

    @Autowired
    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public ResponseEntity<Object> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<Object> get(long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> create(CountryCreateRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(long id, CountryUpdateRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(long id) {
        return null;
    }
}
