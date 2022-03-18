package com.furkanisitan.countrycityapi.model.responses;

import com.furkanisitan.countrycityapi.model.entities.enums.Continent;
import lombok.Data;

@Data
public final class CountryListResponse {

    private Long id;
    private String code;
    private String name;
    private double lifeExpectancy;
    private Continent continent;

}
