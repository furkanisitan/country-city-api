package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.countrycityapi.business.CountryService;
import com.furkanisitan.countrycityapi.business.dtos.city.CityCreateDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityUpdateDto;
import com.furkanisitan.countrycityapi.core.exceptions.EntityNotExistsException;
import com.furkanisitan.countrycityapi.core.exceptions.ForeignKeyConstraintViolationException;
import com.furkanisitan.countrycityapi.dataaccess.CityRepository;
import com.furkanisitan.countrycityapi.entities.City;
import com.furkanisitan.countrycityapi.entities.Country;
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

        CityCreateDto cityCreateDto = new CityCreateDto();
        cityCreateDto.setCountryCode(countryCode);

        City city = new City();
        city.setCountry(country);

        when(countryService.getByCode(cityCreateDto.getCountryCode())).thenReturn(country);
        when(cityRepository.save(any(City.class))).thenReturn(city);


        CityDto cityDto = cityManager.create(cityCreateDto);

        assertAll(
                () -> assertNotNull(cityDto),
                () -> assertEquals(cityCreateDto.getCountryCode(), cityDto.getCountryCode())
        );

    }

    @Test
    void create_ThrowsForeignKeyConstraintViolationException_CountryCodeNotExists() {

        CityCreateDto cityCreateDto = new CityCreateDto();

        when(countryService.getByCode(cityCreateDto.getCountryCode())).thenReturn(null);

        assertThrows(ForeignKeyConstraintViolationException.class, () -> cityManager.create(cityCreateDto));
    }

    @Test
    void update_DoesNotThrowsException() {

        CityUpdateDto cityUpdateDto = new CityUpdateDto();
        City city = new City();
        Country country = new Country();

        when(cityRepository.findById(cityUpdateDto.getId())).thenReturn(Optional.of(city));
        when(countryService.getByCode(cityUpdateDto.getCountryCode())).thenReturn(country);
        when(cityRepository.save(any(City.class))).thenReturn(city);

        assertDoesNotThrow(() -> cityManager.update(cityUpdateDto));
    }

    @Test
    void update_ThrowsEntityNotExistsException_IdNotExists() {

        CityUpdateDto cityUpdateDto = new CityUpdateDto();
        cityUpdateDto.setId(1L);

        when(cityRepository.findById(cityUpdateDto.getId())).thenReturn(Optional.empty());

        assertThrows(EntityNotExistsException.class, () -> cityManager.update(cityUpdateDto));
    }

    @Test
    void update_ThrowsForeignKeyConstraintViolationException_CountryCodeNotExists() {

        CityUpdateDto cityUpdateDto = new CityUpdateDto();
        City city = new City();

        when(cityRepository.findById(cityUpdateDto.getId())).thenReturn(Optional.of(city));
        when(countryService.getByCode(cityUpdateDto.getCountryCode())).thenReturn(null);

        assertThrows(ForeignKeyConstraintViolationException.class, () -> cityManager.update(cityUpdateDto));
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

        assertThrows(EntityNotExistsException.class, () -> cityManager.deleteById(anyLong()));
    }
}