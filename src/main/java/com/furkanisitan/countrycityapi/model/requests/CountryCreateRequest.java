package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import lombok.Data;

import java.util.List;

@Data
public class CountryCreateRequest implements Request {

    private String code;
    private String name;
    private double lifeExpectancy;
    private List<CountryLanguageCreateRequest> languages;
}
