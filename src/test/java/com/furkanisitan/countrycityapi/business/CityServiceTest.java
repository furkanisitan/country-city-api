package com.furkanisitan.countrycityapi.business;

import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CityUpdateRequest;
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

        CityCreateRequest request = new CityCreateRequest();
        request.setName("city_name");
        request.setCountryCode("country_code");
        request.setPopulation(100);

        assertDoesNotThrow(() -> fakeCityService.create(request));
    }

    @ParameterizedTest
    @CsvSource({
            "' ', country_code, 100",
            "city_name, ' ', 100",
            "city_name, country_code, 0"
    })
    void create_ThrowsConstraintViolationException_FieldsIsNotValid(String name, String countryCode, long population) {

        CityCreateRequest request = new CityCreateRequest();
        request.setName(name);
        request.setCountryCode(countryCode);
        request.setPopulation(population);

        assertThrows(ConstraintViolationException.class, () -> fakeCityService.create(request));
    }

    @Test
    void update_DoesNotThrowsException() {

        CityUpdateRequest request = new CityUpdateRequest();
        request.setId(1L);
        request.setName("city_name");
        request.setCountryCode("country_code");
        request.setPopulation(100);

        assertDoesNotThrow(() -> fakeCityService.update(request));
    }

    @ParameterizedTest
    @CsvSource({
            ", city_name, country_code, 100",
            "1, ' ', country_code, 100",
            "1, city_name, ' ', 100",
            "1, city_name, country_code, 0"
    })
    void update_ThrowsConstraintViolationException_FieldsIsNotValid(Long id, String name, String countryCode, long population) {

        CityUpdateRequest request = new CityUpdateRequest();
        request.setId(id);
        request.setName(name);
        request.setCountryCode(countryCode);
        request.setPopulation(population);

        assertThrows(ConstraintViolationException.class, () -> fakeCityService.update(request));
    }

}