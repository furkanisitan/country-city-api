package com.furkanisitan.countrycityapi.entities.enums;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContinentTest {

    @ParameterizedTest
    @CsvSource({"africa, AFRICA", "antarctica, ANTARCTICA", "asia, ASIA", "australia, AUSTRALIA", "europe, EUROPE", "north_america, NORTH_AMERICA", "south_america, SOUTH_AMERICA"})
    void fromString_EnumReturnedByValueIsEqualToSpecifiedEnum(String value, Continent continent) {
        assertEquals(continent, Continent.fromString(value));
    }

    @Test
    void fromString_ThrowIllegalArgumentException_ValueIsInvalid() {

        String value = "afriça";

        assertThrows(IllegalArgumentException.class, () -> Continent.fromString(value));
    }

    @Test
    void fromStringIfPresent_ReturnEmptyOptional_ValueIsInvalid() {

        String value = "afriça";

        assertEquals(Optional.empty(), Continent.fromStringIfPresent(value));
    }
}