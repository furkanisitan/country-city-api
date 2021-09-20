package com.furkanisitan.countrycityapi.business;

import com.furkanisitan.countrycityapi.business.dtos.city.CityCreateDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityUpdateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {FakeCityService.class, ValidationAutoConfiguration.class})
class CityServiceTest {

    @Autowired
    @Qualifier("fakeCityService")
    private CityService fakeCityService;

    @Test
    void create_DoesNotThrowsException() {

        CityCreateDto cityCreateDto = new CityCreateDto();
        cityCreateDto.setName("city_name");
        cityCreateDto.setCountryCode("country_code");
        cityCreateDto.setPopulation(100);

        assertDoesNotThrow(() -> fakeCityService.create(cityCreateDto));
    }

    @ParameterizedTest
    @CsvSource({
            "' ', country_code, 100",
            "city_name, ' ', 100",
            "city_name, country_code, 0"
    })
    void create_ThrowsConstraintViolationException_FieldsIsNotValid(String name, String countryCode, long population) {

        CityCreateDto cityCreateDto = new CityCreateDto();
        cityCreateDto.setName(name);
        cityCreateDto.setCountryCode(countryCode);
        cityCreateDto.setPopulation(population);

        assertThrows(ConstraintViolationException.class, () -> fakeCityService.create(cityCreateDto));
    }

    @Test
    void update_DoesNotThrowsException() {

        CityUpdateDto cityUpdateDto = new CityUpdateDto();
        cityUpdateDto.setId(1L);
        cityUpdateDto.setName("city_name");
        cityUpdateDto.setCountryCode("country_code");
        cityUpdateDto.setPopulation(100);

        assertDoesNotThrow(() -> fakeCityService.update(cityUpdateDto));
    }

    @ParameterizedTest
    @CsvSource({
            ", city_name, country_code, 100",
            "1, ' ', country_code, 100",
            "1, city_name, ' ', 100",
            "1, city_name, country_code, 0"
    })
    void update_ThrowsConstraintViolationException_FieldsIsNotValid(Long id, String name, String countryCode, long population) {

        CityUpdateDto cityUpdateDto = new CityUpdateDto();
        cityUpdateDto.setId(id);
        cityUpdateDto.setName(name);
        cityUpdateDto.setCountryCode(countryCode);
        cityUpdateDto.setPopulation(population);

        assertThrows(ConstraintViolationException.class, () -> fakeCityService.update(cityUpdateDto));
    }

}