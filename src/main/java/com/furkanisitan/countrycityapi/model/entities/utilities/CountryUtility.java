package com.furkanisitan.countrycityapi.model.entities.utilities;

import com.furkanisitan.core.model.utility.EntityUtility;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.entities.CountryLanguage;
import com.furkanisitan.countrycityapi.model.entities.Language;
import com.furkanisitan.countrycityapi.model.entities.ids.CountryLanguageId;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import java.util.Iterator;

public class CountryUtility extends EntityUtility<Country> {

    /**
     * @param clazz the instance of the entity to handle.
     */
    public CountryUtility(Country clazz) {
        super(clazz);
    }

    //region languages

    /**
     * {@code isOfficial} defaults to {@code false}.
     *
     * @see #addLanguage(Language, boolean)
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
     * @implNote It is assumed that {@link CascadeType#ALL} is used
     * in the {@link Country#countryLanguages} definition.
     */
    public void addLanguage(@NonNull Language language, boolean isOfficial) {
        Assert.notNull(language, "language must not be null");

        CountryLanguage countryLanguage = CountryLanguage.from(clazz, language);
        countryLanguage.setOfficial(isOfficial);

        clazz.getCountryLanguages().add(countryLanguage);
    }

    /**
     * Removes the given language from this country.
     *
     * @param language element to be removed from this country.
     * @throws IllegalArgumentException if language is {@literal null}.
     */
    public void removeLanguage(@NonNull Language language) {
        Assert.notNull(language, "language must not be null");

        CountryLanguageId id = new CountryLanguageId(clazz, language);

        for (Iterator<CountryLanguage> iterator = clazz.getCountryLanguages().iterator(); iterator.hasNext(); ) {
            CountryLanguage countryLanguage = iterator.next();

            if (countryLanguage.getId().equals(id)) {
                iterator.remove();
                countryLanguage.setId(null);
            }
        }
    }

    /**
     * Removes all the languages from this country.
     */
    public void clearLanguages() {

        for (CountryLanguage countryLanguage : clazz.getCountryLanguages())
            countryLanguage.setId(null);
        clazz.getCountryLanguages().clear();
    }

    //endregion

}
