package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CountryUpdateRequest implements Request {

    private Long id;
    private String code;
    private String name;
    private double lifeExpectancy;
    private List<CountryLanguageRequest> languages = new ArrayList<>();
    
}
