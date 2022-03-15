package com.furkanisitan.countrycityapi.model.responses;

import lombok.Data;

@Data
public final class CountryCityResponse {

    private Long id;
    private String name;
    private long population;

}
