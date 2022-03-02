package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.core.exceptions.ForeignKeyConstraintException;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.core.exceptions.UniqueConstraintException;
import com.furkanisitan.countrycityapi.business.validators.CountryValidator;
import com.furkanisitan.countrycityapi.business.validators.LanguageValidator;
import com.furkanisitan.countrycityapi.dataaccess.CountryRepository;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.entities.Language;
import com.furkanisitan.countrycityapi.model.requests.*;
import com.furkanisitan.countrycityapi.model.responses.CountryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountryManagerTest {

    @InjectMocks
    private CountryManager countryManager;
    @Mock
    private CountryRepository countryRepository;
    @Mock
    private CountryValidator countryValidator;
    @Mock
    private LanguageValidator languageValidator;

    @Test
    void create_CountryContainsLanguage() {

        CountryCreateRequest request = new CountryCreateRequest();
        CountryLanguageRequest languageRequest = new CountryLanguageRequest();
        languageRequest.setLanguageId(1L);
        request.setLanguages(List.of(languageRequest));
        request.setCode("country_code");

        Language language = new Language();
        Country country = new Country();

        doNothing().when(countryValidator).codeIsUnique(request.getCode());
        when(languageValidator.getIfIdForeignKeyIsExists(languageRequest.getLanguageId())).thenReturn(language);
        when(countryRepository.save(any(Country.class))).thenReturn(country);


        CountryResponse response = countryManager.create(request);


        assertAll(
                () -> assertNotNull(response),
                () -> assertTrue(request.getLanguages().contains(languageRequest))
        );

    }

    @Test
    void create_ThrowsUniqueConstraintException_CountryCodeIsNotUnique() {

        CountryCreateRequest request = new CountryCreateRequest();

        doThrow(UniqueConstraintException.class).when(countryValidator).codeIsUnique(request.getCode());

        assertThrows(UniqueConstraintException.class, () -> countryManager.create(request));
    }

    @Test
    void create_ThrowsForeignKeyConstraintException_LanguageIdNotExists() {

        CountryCreateRequest request = new CountryCreateRequest();
        CountryLanguageRequest languageRequest = new CountryLanguageRequest();
        languageRequest.setLanguageId(1L);
        request.setLanguages(List.of(languageRequest));

        doNothing().when(countryValidator).codeIsUnique(request.getCode());
        when(languageValidator.getIfIdForeignKeyIsExists(anyLong())).thenThrow(ForeignKeyConstraintException.class);

        assertThrows(ForeignKeyConstraintException.class, () -> countryManager.create(request));
    }

    @Test
    void update_DoesNotThrowsException() {

        CountryUpdateRequest request = new CountryUpdateRequest();
        CountryLanguageRequest languageRequest = new CountryLanguageRequest();
        request.setLanguages(List.of(languageRequest));

        Language language = new Language();
        Country country = new Country();

        when(countryValidator.findIfIdIsExists(request.getId())).thenReturn(country);
        doNothing().when(countryValidator).codeIsUnique(request.getCode(), request.getId());
        when(languageValidator.getIfIdForeignKeyIsExists(languageRequest.getLanguageId())).thenReturn(language);
        when(countryRepository.save(any(Country.class))).thenReturn(country);


        assertDoesNotThrow(() -> countryManager.update(request));
    }

    @Test
    void update_ThrowsRecordNotFoundException_IdNotExists() {

        CountryUpdateRequest request = new CountryUpdateRequest();

        doThrow(RecordNotFoundException.class).when(countryValidator).findIfIdIsExists(request.getId());

        assertThrows(RecordNotFoundException.class, () -> countryManager.update(request));
    }

    @Test
    void update_ThrowsUniqueConstraintException_CountryCodeIsNotUnique() {

        CountryUpdateRequest request = new CountryUpdateRequest();
        Country country = new Country();

        when(countryValidator.findIfIdIsExists(request.getId())).thenReturn(country);
        doThrow(UniqueConstraintException.class).when(countryValidator).codeIsUnique(request.getCode(), request.getId());

        assertThrows(UniqueConstraintException.class, () -> countryManager.update(request));
    }

    @Test
    void update_ThrowsForeignKeyConstraintException_LanguageIdNotExists() {

        CountryUpdateRequest request = new CountryUpdateRequest();
        CountryLanguageRequest languageRequest = new CountryLanguageRequest();
        request.setLanguages(List.of(languageRequest));
        Country country = new Country();

        when(countryValidator.findIfIdIsExists(request.getId())).thenReturn(country);
        doNothing().when(countryValidator).codeIsUnique(request.getCode(), request.getId());
        doThrow(ForeignKeyConstraintException.class).when(languageValidator).getIfIdForeignKeyIsExists(languageRequest.getLanguageId());

        assertThrows(ForeignKeyConstraintException.class, () -> countryManager.update(request));
    }

    @Test
    void deleteById_DoesNotThrowsException() {

        doNothing().when(countryValidator).idIsExists(anyLong());

        assertDoesNotThrow(() -> countryManager.deleteById(anyLong()));
    }

    @Test
    void deleteById_ThrowsRecordNotFoundException_IdNotExists() {

        doThrow(RecordNotFoundException.class).when(countryValidator).idIsExists(anyLong());

        assertThrows(RecordNotFoundException.class, () -> countryManager.deleteById(anyLong()));
    }
}