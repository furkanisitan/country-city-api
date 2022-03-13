package com.furkanisitan.countrycityapi.model.responses;

import lombok.Data;

@Data
public final class CountryListResponse {

    private Long id;
    private String code;
    private String name;
    private double lifeExpectancy;

}
