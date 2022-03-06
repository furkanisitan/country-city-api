package com.furkanisitan.countrycityapi.model.responses;

import com.furkanisitan.core.model.Response;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CountryResponse implements Response {

    private Long id;
    private String code;
    private String name;
    private double lifeExpectancy;
    private List<CountryLanguageResponse> languages = new ArrayList<>();

}
