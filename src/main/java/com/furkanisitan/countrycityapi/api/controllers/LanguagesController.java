package com.furkanisitan.countrycityapi.api.controllers;

import com.furkanisitan.core.api.ResponseEntities;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.core.exceptions.RouteBodyMismatchException;
import com.furkanisitan.countrycityapi.api.abstracts.LanguagesApi;
import com.furkanisitan.countrycityapi.business.LanguageService;
import com.furkanisitan.countrycityapi.model.entities.Language;
import com.furkanisitan.countrycityapi.model.requests.LanguageCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.LanguageUpdateRequest;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
public class LanguagesController implements LanguagesApi {

    private final LanguageService languageService;

    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    public ResponseEntity<Object> getAll() {

        return ResponseEntities.ok(languageService.findAll());
    }

    @Override
    public ResponseEntity<Object> get(long id) {

        var response = languageService.findById(id);

        if (response == null)
            throw new RecordNotFoundException(Language.class.getSimpleName(), Pair.of("id", id));

        return ResponseEntities.ok(response);
    }

    @Override
    public ResponseEntity<Object> create(LanguageCreateRequest request) {

        var response = languageService.create(request);

        // location header
        URI uri = MvcUriComponentsBuilder.fromMethodCall(
                on(LanguagesController.class).get(response.getId())).buildAndExpand().toUri();

        return ResponseEntities.created(uri, response);
    }

    @Override
    public ResponseEntity<Object> update(long id, LanguageUpdateRequest request) {

        if (id != request.getId())
            throw new RouteBodyMismatchException("id");

        languageService.update(request);

        return ResponseEntities.noContent();
    }

    @Override
    public ResponseEntity<Object> delete(long id) {

        languageService.deleteById(id);

        return ResponseEntities.noContent();
    }
}
