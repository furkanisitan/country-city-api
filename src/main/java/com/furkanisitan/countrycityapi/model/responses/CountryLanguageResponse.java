package com.furkanisitan.countrycityapi.model.responses;

import com.furkanisitan.core.model.Response;
import lombok.Data;

@Data
public class CountryLanguageResponse implements Response {

    private Long languageId;
    private String languageName;
    private String languageCode;
    private boolean isOfficial;

}
