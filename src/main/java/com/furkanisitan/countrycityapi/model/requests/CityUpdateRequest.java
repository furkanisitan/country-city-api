package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public final class CityUpdateRequest implements Request {

    @Schema(example = "1")
    @NotNull
    private Long id;

    @Schema(example = "Ankara")
    @NotBlank
    private String name;

    @Schema(example = "5747325")
    @Positive
    private long population;

    @Schema(example = "TR")
    @NotBlank
    private String countryCode;

}
