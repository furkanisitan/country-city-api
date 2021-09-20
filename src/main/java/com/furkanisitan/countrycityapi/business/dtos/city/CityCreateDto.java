package com.furkanisitan.countrycityapi.business.dtos.city;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
public class CityCreateDto {

    @NotBlank
    private String name;

    @Positive
    private long population;

    @NotBlank
    private String countryCode;

}
