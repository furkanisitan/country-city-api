package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LanguageUpdateRequest implements Request {

    @Schema(example = "1")
    @NotNull
    private Long id;

    @Schema(example = "tr")
    @NotBlank
    private String code;

    @Schema(example = "Turkish")
    @NotBlank
    private String name;

}
