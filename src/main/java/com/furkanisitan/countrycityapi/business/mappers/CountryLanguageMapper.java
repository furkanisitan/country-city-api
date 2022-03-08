package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.model.entities.CountryLanguage;
import com.furkanisitan.countrycityapi.model.responses.CountryLanguageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper
public interface CountryLanguageMapper {

    CountryLanguageMapper INSTANCE = Mappers.getMapper(CountryLanguageMapper.class);

    List<CountryLanguageResponse> toResponseList(Set<CountryLanguage> source);

    @Mapping(target = "languageId", source = "id.language.id")
    @Mapping(target = "languageName", source = "id.language.name")
    @Mapping(target = "languageCode", source = "id.language.code")
    CountryLanguageResponse toResponse(CountryLanguage source);

}
