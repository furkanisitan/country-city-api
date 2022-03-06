package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class CountryCreateRequest implements Request {

    @Schema(example = "TR")
    @NotBlank
    private String code;

    @Schema(example = "Turkey")
    @NotBlank
    private String name;

    @Schema(example = "78.6")
    private double lifeExpectancy;

    private List<@Valid CountryLanguageRequest> languages = new ArrayList<>();
}
