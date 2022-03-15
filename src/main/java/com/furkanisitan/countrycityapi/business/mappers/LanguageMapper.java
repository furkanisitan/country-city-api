package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.model.entities.Language;
import com.furkanisitan.countrycityapi.model.requests.LanguageCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.LanguageUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.LanguageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface LanguageMapper {

    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    List<LanguageResponse> toResponseList(Collection<Language> source);

    LanguageResponse toResponse(Language source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "countryLanguages", ignore = true)
    Language fromCreateRequest(LanguageCreateRequest source);

    @Mapping(target = "countryLanguages", ignore = true)
    void updateFromUpdateRequest(LanguageUpdateRequest source, @MappingTarget Language target);

}
