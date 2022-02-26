package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.core.exceptions.ForeignKeyConstraintException;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.countrycityapi.business.CountryService;
import com.furkanisitan.countrycityapi.dataaccess.CityRepository;
import com.furkanisitan.countrycityapi.model.entities.City;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CityUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CityResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityManagerTest {

    @InjectMocks
    private CityManager cityManager;
    @Mock
    private CityRepository cityRepository;
    @Mock
    private CountryService countryService;

    @Test
    void create_CountryCodesEquals() {

        String countryCode = "country_code";

        Country country = new Country();
        country.setCode(countryCode);

        CityCreateRequest request = new CityCreateRequest();
        request.setCountryCode(countryCode);

        City city = new City();
        city.setCountry(country);

        when(countryService.getByCode(request.getCountryCode())).thenReturn(country);
        when(cityRepository.save(any(City.class))).thenReturn(city);


        CityResponse response = cityManager.create(request);

        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(request.getCountryCode(), response.getCountryCode())
        );

    }

    @Test
    void create_ThrowsForeignKeyConstraintViolationException_CountryCodeNotExists() {

        CityCreateRequest request = new CityCreateRequest();

        when(countryService.getByCode(request.getCountryCode())).thenReturn(null);

        assertThrows(ForeignKeyConstraintException.class, () -> cityManager.create(request));
    }

    @Test
    void update_DoesNotThrowsException() {

        CityUpdateRequest request = new CityUpdateRequest();
        City city = new City();
        Country country = new Country();

        when(cityRepository.findById(request.getId())).thenReturn(Optional.of(city));
        when(countryService.getByCode(request.getCountryCode())).thenReturn(country);
        when(cityRepository.save(any(City.class))).thenReturn(city);

        assertDoesNotThrow(() -> cityManager.update(request));
    }

    @Test
    void update_ThrowsEntityNotExistsException_IdNotExists() {

        CityUpdateRequest request = new CityUpdateRequest();
        request.setId(1L);

        when(cityRepository.findById(request.getId())).thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, () -> cityManager.update(request));
    }

    @Test
    void update_ThrowsForeignKeyConstraintViolationException_CountryCodeNotExists() {

        CityUpdateRequest request = new CityUpdateRequest();
        City city = new City();

        when(cityRepository.findById(request.getId())).thenReturn(Optional.of(city));
        when(countryService.getByCode(request.getCountryCode())).thenReturn(null);

        assertThrows(ForeignKeyConstraintException.class, () -> cityManager.update(request));
    }

    @Test
    void deleteById_DoesNotThrowsException() {

        when(cityRepository.existsById(anyLong())).thenReturn(Boolean.TRUE);
        doNothing().when(cityRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> cityManager.deleteById(anyLong()));
    }

    @Test
    void deleteById_ThrowsEntityNotExistsException_IdNotExists() {

        when(cityRepository.existsById(anyLong())).thenReturn(Boolean.FALSE);

        assertThrows(RecordNotFoundException.class, () -> cityManager.deleteById(anyLong()));
    }
}