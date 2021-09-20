package com.furkanisitan.countrycityapi.business.dtos.city;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class CityUpdateDto {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @Positive
    private long population;

    @NotBlank
    private String countryCode;

}
