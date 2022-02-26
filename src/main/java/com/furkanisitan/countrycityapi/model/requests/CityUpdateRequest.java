package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CityUpdateRequest implements Request {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @Positive
    private long population;

    @NotBlank
    private String countryCode;

}
