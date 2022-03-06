package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CountryLanguageRequest implements Request {

    @Schema(example = "1")
    @NotNull
    private Long languageId;

    @Schema(example = "true")
    private boolean isOfficial;
}
