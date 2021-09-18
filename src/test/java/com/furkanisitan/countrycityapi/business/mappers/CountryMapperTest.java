package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.business.dtos.country.CountryCreateDto;
import com.furkanisitan.countrycityapi.business.dtos.country.CountryDto;
import com.furkanisitan.countrycityapi.entities.Country;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CountryMapperTest {

    @Test
    void toCountryDto_AllFieldsAreEqualsByCountry() {

        Country country = new Country();
        country.setId(1L);
        country.setCode("country_code");
        country.setName("country_name");
        country.setLifeExpectancy(77);

        CountryDto dto = CountryMapper.INSTANCE.toCountryDto(country);

        assertAll(
                () -> assertEquals(country.getId(), dto.getId()),
                () -> assertEquals(country.getCode(), dto.getCode()),
                () -> assertEquals(country.getName(), dto.getName()),
                () -> assertEquals(country.getLifeExpectancy(), dto.getLifeExpectancy())
        );
    }

    @Test
    void fromCountryCreateDto_AllFieldsAreEqualsByDto() {

        CountryCreateDto dto = new CountryCreateDto();
        dto.setCode("country_code");
        dto.setName("country_name");
        dto.setLifeExpectancy(77);

        Country country = CountryMapper.INSTANCE.fromCountryCreateDto(dto);

        assertAll(
                () -> assertEquals(dto.getCode(), country.getCode()),
                () -> assertEquals(dto.getName(), country.getName()),
                () -> assertEquals(dto.getLifeExpectancy(), country.getLifeExpectancy())
        );
    }

}