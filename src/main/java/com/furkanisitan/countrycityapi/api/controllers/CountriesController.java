package com.furkanisitan.countrycityapi.api.controllers;

import com.furkanisitan.countrycityapi.business.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cities")
public class CountriesController {

    private final CountryService countryService;

    @Autowired
    public CountriesController(CountryService countryService) {
        this.countryService = countryService;
    }
}
