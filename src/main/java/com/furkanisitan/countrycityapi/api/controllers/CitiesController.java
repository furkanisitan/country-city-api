package com.furkanisitan.countrycityapi.api.controllers;

import com.furkanisitan.core.api.ApiHelpers;
import com.furkanisitan.core.api.ResponseEntities;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.core.exceptions.RouteBodyMismatchException;
import com.furkanisitan.countrycityapi.api.abstracts.CitiesApi;
import com.furkanisitan.countrycityapi.business.CityService;
import com.furkanisitan.countrycityapi.model.entities.City;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CityUpdateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
public class CitiesController implements CitiesApi {

    private final CityService cityService;

    @Autowired
    public CitiesController(CityService cityService) {
        this.cityService = cityService;
    }


    @Override
    public ResponseEntity<Object> getAll() {
        return ResponseEntities.ok(cityService.findAll());
    }

    @Override
    public ResponseEntity<Object> get(long id) {
        var response = cityService.findById(id);

        if (response == null)
            throw new RecordNotFoundException(City.class.getSimpleName(), Pair.of("id", id));

        return ResponseEntities.ok(response);
    }

    @Override
    public ResponseEntity<Object> create(CityCreateRequest request) {
        var response = cityService.create(request);
        return ResponseEntities.created(ApiHelpers.getUri(on(this.getClass()).get(response.getId())), response);
    }

    @Override
    public ResponseEntity<Object> update(long id, CityUpdateRequest request) {
        ApiHelpers.validateMismatch(id, request.getId(), "id");

        cityService.update(request);
        return ResponseEntities.noContent();
    }

    @Override
    public ResponseEntity<Object> delete(long id) {
        cityService.deleteById(id);
        return ResponseEntities.noContent();
    }
}
