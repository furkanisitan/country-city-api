package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.responses.CountryResponse;
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

        CountryResponse response = CountryMapper.INSTANCE.toResponse(country);

        assertAll(
                () -> assertEquals(country.getId(), response.getId()),
                () -> assertEquals(country.getCode(), response.getCode()),
                () -> assertEquals(country.getName(), response.getName()),
                () -> assertEquals(country.getLifeExpectancy(), response.getLifeExpectancy())
        );
    }

    @Test
    void fromCountryCreateDto_AllFieldsAreEqualsByDto() {

        CountryCreateRequest request = new CountryCreateRequest();
        request.setCode("country_code");
        request.setName("country_name");
        request.setLifeExpectancy(77);

        Country country = CountryMapper.INSTANCE.fromCreateRequest(request);

        assertAll(
                () -> assertEquals(request.getCode(), country.getCode()),
                () -> assertEquals(request.getName(), country.getName()),
                () -> assertEquals(request.getLifeExpectancy(), country.getLifeExpectancy())
        );
    }

}