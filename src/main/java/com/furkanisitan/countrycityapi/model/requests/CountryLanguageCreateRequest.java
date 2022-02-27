package com.furkanisitan.countrycityapi.model.requests;

import com.furkanisitan.core.model.Request;
import lombok.Data;

@Data
public class CountryLanguageCreateRequest implements Request {

    private Long languageId;
    private boolean isOfficial;
}
