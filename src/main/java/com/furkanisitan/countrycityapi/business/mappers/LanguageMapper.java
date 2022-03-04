package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.model.entities.Language;
import com.furkanisitan.countrycityapi.model.requests.LanguageCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.LanguageUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.LanguageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LanguageMapper {

    LanguageMapper INSTANCE = Mappers.getMapper(LanguageMapper.class);

    List<LanguageResponse> toResponseList(List<Language> source);

    LanguageResponse toResponse(Language source);

    Language fromCreateRequest(LanguageCreateRequest source);

    void updateFromUpdateRequest(LanguageUpdateRequest source, @MappingTarget Language target);

}
