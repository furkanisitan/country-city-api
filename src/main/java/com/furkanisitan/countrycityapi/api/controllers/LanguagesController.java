package com.furkanisitan.countrycityapi.api.controllers;

import com.furkanisitan.core.api.ApiHelpers;
import com.furkanisitan.core.api.ResponseEntities;
import com.furkanisitan.core.criteria.RequestCriteria;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.countrycityapi.api.abstracts.LanguagesApi;
import com.furkanisitan.countrycityapi.business.LanguageService;
import com.furkanisitan.countrycityapi.model.entities.Language;
import com.furkanisitan.countrycityapi.model.requests.LanguageCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.LanguageUpdateRequest;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping(value = "/api/languages", produces = MediaType.APPLICATION_JSON_VALUE)
public class LanguagesController implements LanguagesApi {

    private final LanguageService languageService;

    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @Override
    @GetMapping
    public ResponseEntity<?> all(@RequestParam(required = false) String[] filter,
                                 @RequestParam(required = false) String[] sort,
                                 @RequestParam(required = false) Integer page,
                                 @RequestParam(required = false) Integer size) {

        var criteria = RequestCriteria.of(Language.class, page, size, sort, filter);
        return ResponseEntities.ok(languageService.findAll(criteria));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable long id) {
        var response = languageService.findById(id);

        if (response == null)
            throw new RecordNotFoundException(Language.class.getSimpleName(), Pair.of("id", id));

        return ResponseEntities.ok(response);
    }

    @Override
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody LanguageCreateRequest request) {
        var response = languageService.create(request);
        return ResponseEntities.created(ApiHelpers.getUri(on(this.getClass()).one(response.getId())), response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @Valid @RequestBody LanguageUpdateRequest request) {
        ApiHelpers.validateMismatch(id, request.getId(), "id");

        languageService.update(request);
        return ResponseEntities.noContent();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        languageService.deleteById(id);
        return ResponseEntities.noContent();
    }
}
