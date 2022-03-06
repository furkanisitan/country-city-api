package com.furkanisitan.countrycityapi.model.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CountryCreateResponse {

    private Long id;
    private String code;
    private String name;
    private double lifeExpectancy;
    private List<CountryLanguageResponse> languages = new ArrayList<>();

}
