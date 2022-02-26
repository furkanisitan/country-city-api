package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.model.entities.City;
import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CityUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CityResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    List<CityResponse> toResponseList(List<City> source);

    @Mapping(target = "countryCode", source = "country.code")
    CityResponse toResponse(City source);

    City fromCreateRequest(CityCreateRequest source);

    void updateFromUpdateRequest(CityUpdateRequest source, @MappingTarget City target);

}
