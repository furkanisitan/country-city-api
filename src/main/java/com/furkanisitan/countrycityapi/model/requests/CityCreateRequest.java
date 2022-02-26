package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class CityCreateRequest implements Request {

    @NotBlank
    private String name;

    @Positive
    private long population;

    @NotBlank
    private String countryCode;

}
