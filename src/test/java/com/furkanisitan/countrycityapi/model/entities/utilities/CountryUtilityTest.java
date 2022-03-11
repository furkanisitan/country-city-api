package com.furkanisitan.countrycityapi.model.entities.utilities;

import com.furkanisitan.countrycityapi.dataaccess.CountryRepository;
import com.furkanisitan.countrycityapi.dataaccess.LanguageRepository;
import com.furkanisitan.countrycityapi.model.entities.CountryLanguage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@Sql(value = "/test-init.sql")
class CountryUtilityTest {

    private final Long COUNTRY_ID = 1L;
    private final Long LANGUAGE_ID_NEW = 1L;
    private final Long LANGUAGE_ID_EXISTS = 2L;

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private LanguageRepository languageRepository;

    @Test
    void addLanguage_CountryContainsLanguage_AddLanguageToCountry() {

        var language = languageRepository.findById(LANGUAGE_ID_NEW)
                .orElseThrow(() -> new RuntimeException("Language not exists."));

        var country = countryRepository.findById(COUNTRY_ID)
                .orElseThrow(() -> new RuntimeException("Country not exists."));

        var countryLanguage = CountryLanguage.from(country, language);


        country.utility().addLanguage(language);
        countryRepository.save(country);

        var updatedCountry = countryRepository.findById(COUNTRY_ID)
                .orElseThrow(() -> new RuntimeException("Country not exists."));

        assertTrue(updatedCountry.getCountryLanguages().contains(countryLanguage));
    }

    @Test
    void removeLanguage_CountryNotContainsLanguage_RemoveLanguageFromCountry() {

        var language = languageRepository.findById(LANGUAGE_ID_EXISTS)
                .orElseThrow(() -> new RuntimeException("Language not exists."));

        var country = countryRepository.findById(COUNTRY_ID)
                .orElseThrow(() -> new RuntimeException("Country not exists."));

        var countryLanguage = CountryLanguage.from(country, language);


        country.utility().removeLanguage(language);
        countryRepository.save(country);


        var updatedCountry = countryRepository.findById(COUNTRY_ID)
                .orElseThrow(() -> new RuntimeException("Country not exists."));


        assertFalse(updatedCountry.getCountryLanguages().contains(countryLanguage));
    }

    @Test
    void clearLanguages_LanguageCountIsZero_RemoveAllLanguagesFromCountry() {

        var country = countryRepository.findById(COUNTRY_ID)
                .orElseThrow(() -> new RuntimeException("Country not exists."));


        country.utility().clearLanguages();

        var updatedCountry = countryRepository.findById(COUNTRY_ID)
                .orElseThrow(() -> new RuntimeException("Country not exists."));


        assertEquals(0, updatedCountry.getCountryLanguages().size());
    }
}