package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.business.dtos.city.CityCreateDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityUpdateDto;
import com.furkanisitan.countrycityapi.entities.City;
import com.furkanisitan.countrycityapi.entities.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CityMapperTest {

    @Test
    void toCityDto_AllFieldsAreEqualsByCity() {

        City city = new City();
        city.setId(1L);
        city.setName("city_name");
        city.setPopulation(22);

        CityDto dto = CityMapper.INSTANCE.toCityDto(city);

        assertAll(
                () -> assertEquals(city.getId(), dto.getId()),
                () -> assertEquals(city.getName(), dto.getName()),
                () -> assertEquals(city.getPopulation(), dto.getPopulation())
        );
    }

    @Test
    void fromCityCreateDto_AllFieldsAreEqualsByDto() {

        CityCreateDto dto = new CityCreateDto();
        dto.setName("city_name");
        dto.setPopulation(22);
        dto.setCountryCode("country_code");

        City city = CityMapper.INSTANCE.fromCityCreateDto(dto);

        assertAll(
                () -> assertEquals(dto.getName(), city.getName()),
                () -> assertEquals(dto.getPopulation(), city.getPopulation())
        );
    }

    @Test
    void updateFromCityUpdateDto_AllFieldsAreEqualsByDtoAndOtherFieldsOfCityNotChange() {

        CityUpdateDto dto = new CityUpdateDto();
        dto.setId(1L);
        dto.setName("city_name");
        dto.setPopulation(22);
        dto.setCountryCode("country_code");

        Country country = new Country();
        country.setCode("country_code");

        City city = new City();
        city.setId(1L);
        city.setName("city");
        city.setPopulation(11);
        city.setCountry(country);

        CityMapper.INSTANCE.updateFromCityUpdateDto(dto, city);

        assertAll(
                () -> assertEquals(dto.getId(), city.getId()),
                () -> assertEquals(dto.getName(), city.getName()),
                () -> assertEquals(dto.getPopulation(), city.getPopulation()),
                () -> assertEquals(country, city.getCountry())
        );
    }

}