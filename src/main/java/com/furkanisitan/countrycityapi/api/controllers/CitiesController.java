package com.furkanisitan.countrycityapi.api.controllers;

import com.furkanisitan.core.api.ApiHelpers;
import com.furkanisitan.core.api.ResponseEntities;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.countrycityapi.api.abstracts.CitiesApi;
import com.furkanisitan.countrycityapi.business.CityService;
import com.furkanisitan.countrycityapi.model.entities.City;
import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CityUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping(value = "/api/cities", produces = MediaType.APPLICATION_JSON_VALUE)
public class CitiesController implements CitiesApi {

    private final CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }


    @Override
    @GetMapping
    public ResponseEntity<?> all() {
        return ResponseEntities.ok(cityService.findAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable long id) {
        var response = cityService.findById(id);

        if (response == null)
            throw new RecordNotFoundException(City.class.getSimpleName(), Pair.of("id", id));

        return ResponseEntities.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody CityCreateRequest request) {
        var response = cityService.create(request);
        return ResponseEntities.created(ApiHelpers.getUri(on(this.getClass()).one(response.getId())), response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody CityUpdateRequest request) {
        ApiHelpers.validateMismatch(id, request.getId(), "id");

        cityService.update(request);
        return ResponseEntities.noContent();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        cityService.deleteById(id);
        return ResponseEntities.noContent();
    }
}
