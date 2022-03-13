package com.furkanisitan.countrycityapi.model.responses;

import com.furkanisitan.core.model.Response;
import lombok.Data;

@Data
public final class CityListResponse implements Response {

    private Long id;
    private String name;
    private long population;
    private Long countryId;

}
