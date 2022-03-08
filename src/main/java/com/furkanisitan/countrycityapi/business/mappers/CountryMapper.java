package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.entities.CountryLanguage;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CountryCreateResponse;
import com.furkanisitan.countrycityapi.model.responses.CountryLanguageResponse;
import com.furkanisitan.countrycityapi.model.responses.CountryListResponse;
import com.furkanisitan.countrycityapi.model.responses.CountryResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    List<CountryListResponse> toResponseList(List<Country> source);

    @Mapping(target = "languages", source = "countryLanguages", qualifiedByName = "toCountryLanguageResponseList")
    CountryResponse toResponse(Country source);

    CountryListResponse toListResponse(Country source);

    @Mapping(target = "languages", source = "countryLanguages")
    CountryCreateResponse toCreateResponse(Country source);

    @Mapping(target = "id", ignore = true)
    Country fromCreateRequest(CountryCreateRequest source);

    void updateFromUpdateRequest(CountryUpdateRequest source, @MappingTarget Country target);

    @IterableMapping(qualifiedByName = "toCountryLanguageResponse")
    @Named("toCountryLanguageResponseList")
    List<CountryLanguageResponse> toCountryLanguageResponseList(Collection<CountryLanguage> source);

    @Mapping(target = "languageId", source = "id.language.id")
    @Named("toCountryLanguageResponse")
    CountryLanguageResponse toCountryLanguageResponse(CountryLanguage source);

}
