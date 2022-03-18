package com.furkanisitan.countrycityapi.business;

import com.furkanisitan.core.criteria.RequestCriteria;
import com.furkanisitan.countrycityapi.model.entities.enums.Continent;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryLanguageRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CountryCreateResponse;
import com.furkanisitan.countrycityapi.model.responses.CountryListResponse;
import com.furkanisitan.countrycityapi.model.responses.CountryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {FakeCountryService.class, ValidationAutoConfiguration.class})
class CountryServiceTest {

    @Autowired
    @Qualifier("fakeCountryService")
    private CountryService fakeCountryService;

    @Test
    void create_DoesNotThrowsException() {

        CountryCreateRequest request = new CountryCreateRequest();
        request.setName("country_name");
        request.setCode("country_code");
        request.setContinent(Continent.AFRICA);

        assertDoesNotThrow(() -> fakeCountryService.create(request));
    }

    @ParameterizedTest
    @CsvSource({
            "' ', country_name",
            "city_code, ' '",
    })
    void create_ThrowsConstraintViolationException_FieldsIsNotValid(String code, String name) {

        CountryCreateRequest request = new CountryCreateRequest();
        request.setCode(code);
        request.setName(name);

        assertThrows(ConstraintViolationException.class, () -> fakeCountryService.create(request));
    }

    @Test
    void create_ThrowsConstraintViolationException_LanguageRequestFieldsIsNotValid() {

        CountryCreateRequest request = new CountryCreateRequest();
        request.setName("country_name");
        request.setCode("country_code");

        CountryLanguageRequest languageRequest = new CountryLanguageRequest();
        languageRequest.setLanguageId(null);

        request.setLanguages(List.of(languageRequest));

        assertThrows(ConstraintViolationException.class, () -> fakeCountryService.create(request));
    }

    @Test
    void update_DoesNotThrowsException() {

        CountryUpdateRequest request = new CountryUpdateRequest();
        request.setId(1L);
        request.setCode("country_code");
        request.setName("country_name");
        request.setContinent(Continent.AFRICA);

        assertDoesNotThrow(() -> fakeCountryService.update(request));
    }

    @ParameterizedTest
    @CsvSource({
            ", country_code, country_name",
            "1, ' ', country_name",
            "1, country_code, ' '",
    })
    void update_ThrowsConstraintViolationException_FieldsIsNotValid(Long id, String code, String name) {

        CountryUpdateRequest request = new CountryUpdateRequest();
        request.setId(id);
        request.setCode(code);
        request.setName(name);

        assertThrows(ConstraintViolationException.class, () -> fakeCountryService.update(request));
    }

}

@Service
@Qualifier("fakeCountryService")
class FakeCountryService implements CountryService {
    @Override
    public <V> boolean existsBy(String name, V value) {
        return false;
    }

    @Override
    public List<CountryListResponse> findAll() {
        return null;
    }

    @Override
    public List<CountryListResponse> findAll(RequestCriteria criteria) {
        return null;
    }

    @Nullable
    @Override
    public CountryResponse findById(Long id) {
        return null;
    }

    @Override
    public CountryCreateResponse create(CountryCreateRequest request) {
        return null;
    }

    @Override
    public void update(CountryUpdateRequest request) {

    }

    @Override
    public void deleteById(Long id) {

    }
}