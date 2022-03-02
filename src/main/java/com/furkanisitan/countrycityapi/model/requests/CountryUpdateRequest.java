package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class CountryUpdateRequest implements Request {

    @NotNull
    private Long id;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private double lifeExpectancy;

    private List<@Valid CountryLanguageRequest> languages = new ArrayList<>();
    
}
