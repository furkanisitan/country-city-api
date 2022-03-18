package com.furkanisitan.countrycityapi.model.responses;

import com.furkanisitan.core.model.Response;
import com.furkanisitan.countrycityapi.model.entities.enums.Continent;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public final class CountryResponse implements Response {

    private Long id;
    private String code;
    private String name;
    private double lifeExpectancy;
    private Continent continent;
    private List<CountryLanguageResponse> languages = new ArrayList<>();
    private List<CountryCityResponse> cities = new ArrayList<>();

}
