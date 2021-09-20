package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.business.dtos.city.CityCreateDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityUpdateDto;
import com.furkanisitan.countrycityapi.entities.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    List<CityDto> toCityDtoList(List<City> source);

    @Mapping(target = "countryCode", source = "country.code")
    CityDto toCityDto(City source);

    City fromCityCreateDto(CityCreateDto source);

    void updateFromCityUpdateDto(CityUpdateDto source, @MappingTarget City target);

}
