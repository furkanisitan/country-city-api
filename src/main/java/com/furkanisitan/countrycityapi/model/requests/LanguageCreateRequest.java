package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public final class LanguageCreateRequest implements Request {

    @Schema(example = "tr")
    @NotBlank
    private String code;

    @Schema(example = "Turkish")
    @NotBlank
    private String name;

}

