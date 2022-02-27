package com.furkanisitan.countrycityapi.business.mappers;

import com.furkanisitan.countrycityapi.model.entities.City;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CityUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CityResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CityMapperTest {

    @Test
    void toCityDto_AllFieldsAreEqualsByCity() {

        Country country = new Country();
        country.setCode("country_code");

        City city = new City();
        city.setId(1L);
        city.setName("city_name");
        city.setPopulation(22);
        city.setCountry(country);


        CityResponse response = CityMapper.INSTANCE.toResponse(city);


        assertAll(
                () -> assertEquals(city.getId(), response.getId()),
                () -> assertEquals(city.getName(), response.getName()),
                () -> assertEquals(city.getPopulation(), response.getPopulation()),
                () -> assertEquals(city.getCountry().getCode(), response.getCountryCode())
        );
    }

    @Test
    void fromCityCreateDto_AllFieldsAreEqualsByDto() {

        CityCreateRequest request = new CityCreateRequest();
        request.setName("city_name");
        request.setPopulation(22);
        request.setCountryCode("country_code");

        City city = CityMapper.INSTANCE.fromCreateRequest(request);

        assertAll(
                () -> assertEquals(request.getName(), city.getName()),
                () -> assertEquals(request.getPopulation(), city.getPopulation())
        );
    }

    @Test
    void updateFromCityUpdateDto_AllFieldsAreEqualsByDtoAndOtherFieldsOfCityNotChange() {

        CityUpdateRequest request = new CityUpdateRequest();
        request.setId(1L);
        request.setName("city_name");
        request.setPopulation(22);
        request.setCountryCode("country_code");

        Country country = new Country();
        country.setCode("country_code");

        City city = new City();
        city.setId(1L);
        city.setName("city");
        city.setPopulation(11);
        city.setCountry(country);

        CityMapper.INSTANCE.updateFromUpdateRequest(request, city);

        assertAll(
                () -> assertEquals(request.getId(), city.getId()),
                () -> assertEquals(request.getName(), city.getName()),
                () -> assertEquals(request.getPopulation(), city.getPopulation()),
                () -> assertEquals(country, city.getCountry())
        );
    }

}