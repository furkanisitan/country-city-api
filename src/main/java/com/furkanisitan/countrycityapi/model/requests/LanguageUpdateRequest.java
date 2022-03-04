package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LanguageUpdateRequest implements Request {

    @NotNull
    private Long id;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

}
