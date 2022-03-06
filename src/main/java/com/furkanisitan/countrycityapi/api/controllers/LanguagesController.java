package com.furkanisitan.countrycityapi.api.controllers;

import com.furkanisitan.core.api.ResponseEntities;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.core.exceptions.RouteBodyMismatchException;
import com.furkanisitan.countrycityapi.business.LanguageService;
import com.furkanisitan.countrycityapi.model.entities.Language;
import com.furkanisitan.countrycityapi.model.requests.LanguageCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.LanguageUpdateRequest;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.on;

@RestController
@RequestMapping("/api/languages")
public class LanguagesController {

    private final LanguageService languageService;

    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return ResponseEntities.ok(languageService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> get(@PathVariable long id) {

        var response = languageService.findById(id);

        if (response == null)
            throw new RecordNotFoundException(Language.class.getSimpleName(), Pair.of("id", id));

        return ResponseEntities.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody LanguageCreateRequest request) {

        var response = languageService.create(request);

        // location header
        URI uri = MvcUriComponentsBuilder.fromMethodCall(
                on(LanguagesController.class).get(response.getId())).buildAndExpand().toUri();

        return ResponseEntities.created(uri, response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable long id, @RequestBody LanguageUpdateRequest request) {

        if (id != request.getId())
            throw new RouteBodyMismatchException("id");

        languageService.update(request);

        return ResponseEntities.noContent();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable long id) {

        languageService.deleteById(id);

        return ResponseEntities.noContent();
    }

}
