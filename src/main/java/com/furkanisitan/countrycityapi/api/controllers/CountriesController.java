package com.furkanisitan.countrycityapi.api.controllers;

import com.furkanisitan.core.api.ApiHelpers;
import com.furkanisitan.core.api.ResponseEntities;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.countrycityapi.api.abstracts.CountriesApi;
import com.furkanisitan.countrycityapi.business.CityService;
import com.furkanisitan.countrycityapi.business.CountryService;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.entities.Country_;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping(value = "/api/countries", produces = MediaType.APPLICATION_JSON_VALUE)
public class CountriesController implements CountriesApi {

    private final CountryService countryService;
    private final CityService cityService;

    @Autowired
    public CountriesController(CountryService countryService, CityService cityService) {
        this.countryService = countryService;
        this.cityService = cityService;
    }

    @Override
    @GetMapping
    public ResponseEntity<?> all() {
        return ResponseEntities.ok(countryService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable long id) {
        var response = countryService.findById(id);

        if (response == null)
            throw new RecordNotFoundException(Country.class.getSimpleName(), Pair.of("id", id));

        return ResponseEntities.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CountryCreateRequest request) {
        var response = countryService.create(request);
        return ResponseEntities.created(ApiHelpers.getUri(on(this.getClass()).one(response.getId())), response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody CountryUpdateRequest request) {
        ApiHelpers.validateMismatch(id, request.getId(), "id");

        countryService.update(request);
        return ResponseEntities.noContent();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        countryService.deleteById(id);
        return ResponseEntities.noContent();
    }

    @Override
    @GetMapping("/{id}/cities")
    public ResponseEntity<?> allCities(@PathVariable long id) {

        if (!countryService.existsBy(Country_.ID, id))
            throw new RecordNotFoundException(Country.class.getSimpleName(), Pair.of("id", id));

        return ResponseEntities.ok(cityService.findAllByCountryId(id));
    }

}
