package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LanguageCreateRequest implements Request {

    @NotBlank
    private String code;

    @NotBlank
    private String name;

}

