package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class CountryCreateRequest implements Request {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    private double lifeExpectancy;

    private List<@Valid CountryLanguageRequest> languages = new ArrayList<>();
}
