package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CountryLanguageRequest implements Request {

    @NotNull
    private Long languageId;

    private boolean isOfficial;
}
