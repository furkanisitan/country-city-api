package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.business.dtos.country.CountryCreateDto;
import com.furkanisitan.countrycityapi.business.dtos.country.CountryDto;
import com.furkanisitan.countrycityapi.business.dtos.country.CountryUpdateDto;
import com.furkanisitan.countrycityapi.entities.Country;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CountryMapper {

    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    List<CountryDto> toCountryDtoList(List<Country> source);

    CountryDto toCountryDto(Country source);

    Country fromCountryCreateDto(CountryCreateDto source);

    void updateFromCountryUpdateDto(CountryUpdateDto source, @MappingTarget Country target);

}
