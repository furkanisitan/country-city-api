package com.furkanisitan.countrycityapi.api.controllers;

import com.furkanisitan.core.api.ResponseEntities;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.core.exceptions.RouteBodyMismatchException;
import com.furkanisitan.countrycityapi.api.abstracts.CountriesApi;
import com.furkanisitan.countrycityapi.business.CountryService;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.entities.Language;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
public class CountriesController implements CountriesApi {

    private final CountryService countryService;

    @Autowired
    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public ResponseEntity<Object> getAll() {
        return ResponseEntities.ok(countryService.findAll());
    }

    @Override
    public ResponseEntity<Object> get(long id) {
        var response = countryService.findById(id);

        if (response == null)
            throw new RecordNotFoundException(Country.class.getSimpleName(), Pair.of("id", id));

        return ResponseEntities.ok(response);
    }

    @Override
    public ResponseEntity<Object> create(CountryCreateRequest request) {
        var response = countryService.create(request);

        // location header
        URI uri = MvcUriComponentsBuilder.fromMethodCall(
                on(CountriesController.class).get(response.getId())).buildAndExpand().toUri();

        return ResponseEntities.created(uri, response);
    }

    @Override
    public ResponseEntity<Object> update(long id, CountryUpdateRequest request) {
        if (id != request.getId())
            throw new RouteBodyMismatchException("id");

        countryService.update(request);
        return ResponseEntities.noContent();
    }

    @Override
    public ResponseEntity<Object> delete(long id) {
        countryService.deleteById(id);
        return ResponseEntities.noContent();
    }
}
