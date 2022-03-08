package com.furkanisitan.countrycityapi.api.controllers;

import com.furkanisitan.core.api.ApiHelpers;
import com.furkanisitan.core.api.ResponseEntities;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.countrycityapi.api.abstracts.LanguagesApi;
import com.furkanisitan.countrycityapi.business.LanguageService;
import com.furkanisitan.countrycityapi.model.entities.Language;
import com.furkanisitan.countrycityapi.model.requests.LanguageCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.LanguageUpdateRequest;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
        return ResponseEntities.created(ApiHelpers.getUri(on(this.getClass()).get(response.getId())), response);
    }

    @Override
    public ResponseEntity<Object> update(long id, LanguageUpdateRequest request) {
        ApiHelpers.validateMismatch(id, request.getId(), "id");

        languageService.update(request);
        return ResponseEntities.noContent();
    }

    @Override
    public ResponseEntity<Object> delete(long id) {

        languageService.deleteById(id);
        return ResponseEntities.noContent();
    }
}
