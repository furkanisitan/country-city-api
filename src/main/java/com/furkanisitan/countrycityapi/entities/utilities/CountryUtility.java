package com.furkanisitan.countrycityapi.entities.utilities;

import com.furkanisitan.countrycityapi.core.entities.EntityUtility;
import com.furkanisitan.countrycityapi.entities.City;
import com.furkanisitan.countrycityapi.entities.Country;
import com.furkanisitan.countrycityapi.entities.CountryLanguage;
import com.furkanisitan.countrycityapi.entities.Language;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

public class CountryUtility extends EntityUtility<Country> {

    /**
     * @param clazz the instance of the entity to handle.
     */
    public CountryUtility(Country clazz) {
        super(clazz);
    }

    //region cities

    /**
     * Adds the given city to this country.
     *
     * @param city element to be added to this country.
     * @throws IllegalArgumentException if city is {@literal null}.
     */
    public void addCity(@NonNull City city) {
        Assert.notNull(city, "city must not be null");
        clazz.getCities().add(city);
        city.setCountry(clazz);
    }

    /**
     * Removes the given city from this country.
     *
     * @param city element to be removed from this country.
     * @throws IllegalArgumentException if city is {@literal null}.
     */
    public void removeCity(@NonNull City city) {
        Assert.notNull(city, "city must not be null");
        clazz.getCities().remove(city);
        city.setCountry(null);
    }

    /**
     * Removes all of the cities from this country.
     */
    public void clearCities() {
        for (City city : clazz.getCities())
            city.setCountry(null);
        clazz.getCities().clear();
    }
    //endregion

    /**
     * Adds the given language to this country.
     *
     * @param language element to be added to this country.
     * @throws IllegalArgumentException if language is {@literal null}.
     */
    public void addLanguage(@NonNull Language language) {
        Assert.notNull(language, "language must not be null");
        addLanguage(language, false);
    }

    /**
     * Adds the given language to this country.
     *
     * @param language   element to be added to this country.
     * @param isOfficial the value to be assigned to 'official' field of {@link CountryLanguage} class.
     * @throws IllegalArgumentException if language is {@literal null}.
     */
    public void addLanguage(@NonNull Language language, boolean isOfficial) {
        Assert.notNull(language, "language must not be null");

        CountryLanguage countryLanguage = CountryLanguage.from(clazz, language);
        countryLanguage.setOfficial(isOfficial);

        clazz.getCountryLanguages().add(countryLanguage);
        language.getCountryLanguages().add(countryLanguage);
    }

    /**
     * Removes the given language from this country.
     *
     * @param language element to be removed from this country.
     * @throws IllegalArgumentException if language is {@literal null}.
     */
    public void removeLanguage(@NonNull Language language) {
        // TODO Implement this method when writing test methods.
    }

    /**
     * Removes all of the languages from this country.
     */
    public void clearLanguages() {
        // TODO Implement this method when writing test methods.
    }

}
