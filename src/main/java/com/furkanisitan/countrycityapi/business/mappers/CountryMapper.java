package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CountryCreateResponse;
import com.furkanisitan.countrycityapi.model.responses.CountryListResponse;
import com.furkanisitan.countrycityapi.model.responses.CountryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper(uses = {CountryLanguageMapper.class, CityMapper.class})
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    List<CountryListResponse> toResponseList(Collection<Country> source);

    @Mapping(target = "languages", source = "countryLanguages")
    CountryResponse toResponse(Country source);

    CountryListResponse toListResponse(Country source);

    @Mapping(target = "languages", source = "countryLanguages")
    CountryCreateResponse toCreateResponse(Country source);

    @Mapping(target = "id", ignore = true)
    Country fromCreateRequest(CountryCreateRequest source);

    void updateFromUpdateRequest(CountryUpdateRequest source, @MappingTarget Country target);

}
