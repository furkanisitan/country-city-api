package com.furkanisitan.countrycityapi.model.responses;

import com.furkanisitan.countrycityapi.model.entities.enums.Continent;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public final class CountryCreateResponse {

    private Long id;
    private String code;
    private String name;
    private double lifeExpectancy;
    private Continent continent;
    private List<CountryLanguageResponse> languages = new ArrayList<>();

}
