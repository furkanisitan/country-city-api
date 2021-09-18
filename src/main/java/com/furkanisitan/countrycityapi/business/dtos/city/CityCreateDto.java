package com.furkanisitan.countrycityapi.business.dtos.city;

import lombok.Data;

@Data
public class CityCreateDto {

    private String name;
    private long population;
    private String countryCode;

}
