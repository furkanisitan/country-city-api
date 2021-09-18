package com.furkanisitan.countrycityapi.business.dtos.country;

import lombok.Data;

@Data
public class CountryUpdateDto {

    private Long id;
    private String code;
    private String name;
    private double lifeExpectancy;

}
