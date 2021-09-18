package com.furkanisitan.countrycityapi.business.dtos.country;

import lombok.Data;

@Data
public class CountryCreateDto {

    private String code;
    private String name;
    private double lifeExpectancy;

}
