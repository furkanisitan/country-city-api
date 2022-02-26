package com.furkanisitan.countrycityapi.model.responses;

import com.furkanisitan.core.model.Response;
import lombok.Data;

@Data
public class CityResponse implements Response {

    private Long id;
    private String name;
    private long population;
    private String countryCode;

}
