package com.furkanisitan.countrycityapi.model.entities.utilities;

import com.furkanisitan.countrycityapi.dataaccess.CountryRepository;
import com.furkanisitan.countrycityapi.dataaccess.LanguageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles("test")
@Sql(value = "/test-init.sql")
@Sql(value = "/test-clean.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class CountryUtilityTest {

    private CountryRepository countryRepository;
    private LanguageRepository languageRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void Test() {

    }
}