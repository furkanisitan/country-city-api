package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public final class CountryUpdateRequest implements Request {

    @Schema(example = "1")
    @NotNull
    private Long id;

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
