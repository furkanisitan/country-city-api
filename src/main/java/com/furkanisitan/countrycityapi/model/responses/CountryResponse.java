package com.furkanisitan.countrycityapi.model.responses;

import com.furkanisitan.core.model.Response;
import lombok.Data;

@Data
public class CountryResponse implements Response {

    private Long id;
    private String code;
    private String name;
    private double lifeExpectancy;

}
