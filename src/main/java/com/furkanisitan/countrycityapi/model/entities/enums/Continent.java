package com.furkanisitan.countrycityapi.model.entities.enums;

import lombok.Getter;

import java.util.Locale;
import java.util.Optional;

@Getter
public enum Continent {
    AFRICA, ANTARCTICA, ASIA, AUSTRALIA, EUROPE, NORTH_AMERICA, SOUTH_AMERICA;

    /**
     * Returns the {@link Continent} enum for the given {@link String} value.
     *
     * @throws IllegalArgumentException in case the given value cannot be parsed into an enum value.
     */
    public static Continent fromString(String value) {
        try {
            return valueOf(value.toUpperCase(Locale.US));
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Invalid value '%s' for continent! Has to be either 'africa', 'antarctica', 'asia', 'australia', 'europe', 'north_america' or 'south_america' (case insensitive).", value));
        }
    }

    /**
     * Returns an optional {@link Continent} enum for the given {@link String} value.
     */
    public static Optional<Continent> fromStringIfPresent(String value) {
        try {
            return Optional.of(fromString(value));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

}
