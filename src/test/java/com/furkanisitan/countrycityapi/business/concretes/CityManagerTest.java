package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.core.exceptions.ForeignKeyConstraintException;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.countrycityapi.business.validators.CityValidator;
import com.furkanisitan.countrycityapi.business.validators.CountryValidator;
import com.furkanisitan.countrycityapi.dataaccess.CityRepository;
import com.furkanisitan.countrycityapi.model.entities.City;
import com.furkanisitan.countrycityapi.model.entities.City_;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.entities.Country_;
import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CityUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CityResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CityManagerTest {

    @InjectMocks
    private CityManager cityManager;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private CityValidator cityValidator;
    @Mock
    private CountryValidator countryValidator;

    @Test
    void create_CountryCodesEquals() {

        String countryCode = "country_code";

        Country country = new Country();
        country.setCode(countryCode);

        CityCreateRequest request = new CityCreateRequest();
        request.setCountryCode(countryCode);

        City city = new City();
        city.setCountry(country);

        when(countryValidator.findForeignBy(Country_.CODE, request.getCountryCode())).thenReturn(country);
        when(cityRepository.save(any(City.class))).thenReturn(city);


        CityResponse response = cityManager.create(request);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(request.getCountryCode(), response.getCountry().getCode())
        );

    }

    @Test
    void create_ThrowsForeignKeyConstraintException_CountryCodeNotExists() {

        CityCreateRequest request = new CityCreateRequest();

        when(countryValidator.findForeignBy(Country_.CODE, request.getCountryCode()))
                .thenThrow(ForeignKeyConstraintException.class);

        assertThrows(ForeignKeyConstraintException.class, () -> cityManager.create(request));
    }

    @Test
    void update_DoesNotThrowsException() {

        CityUpdateRequest request = new CityUpdateRequest();
        City city = new City();
        Country country = new Country();

        when(cityValidator.findBy(City_.ID, request.getId())).thenReturn(city);
        when(countryValidator.findForeignBy(Country_.CODE, request.getCountryCode())).thenReturn(country);
        when(cityRepository.save(any(City.class))).thenReturn(city);

        assertDoesNotThrow(() -> cityManager.update(request));
    }

    @Test
    void update_ThrowsRecordNotFoundException_IdNotExists() {

        CityUpdateRequest request = new CityUpdateRequest();
        request.setId(1L);

        when(cityValidator.findBy(City_.ID, request.getId()))
                .thenThrow(RecordNotFoundException.class);

        assertThrows(RecordNotFoundException.class, () -> cityManager.update(request));
    }

    @Test
    void update_ThrowsForeignKeyConstraintException_CountryCodeNotExists() {

        CityUpdateRequest request = new CityUpdateRequest();
        City city = new City();

        when(cityValidator.findBy(City_.ID, request.getId())).thenReturn(city);
        when(countryValidator.findForeignBy(Country_.CODE, request.getCountryCode()))
                .thenThrow(ForeignKeyConstraintException.class);

        assertThrows(ForeignKeyConstraintException.class, () -> cityManager.update(request));
    }

    @Test
    void deleteById_DoesNotThrowsException() {

        var id = 0L;
        doNothing().when(cityValidator).existsBy(City_.ID, id);

        assertDoesNotThrow(() -> cityManager.deleteById(id));
    }

    @Test
    void deleteById_ThrowsRecordNotFoundException_IdNotExists() {

        var id = 0L;
        doThrow(RecordNotFoundException.class).when(cityValidator).existsBy(City_.ID, id);

        assertThrows(RecordNotFoundException.class, () -> cityManager.deleteById(id));
    }
}