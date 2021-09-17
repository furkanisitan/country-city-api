package com.furkanisitan.countrycityapi.entities;

import com.furkanisitan.countrycityapi.core.entities.CompositeEntity;
import com.furkanisitan.countrycityapi.entities.ids.CountryLanguageId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "country_languages")
public class CountryLanguage extends CompositeEntity<CountryLanguageId> {

    @Column(name = "is_official")
    private boolean official;

    public static CountryLanguage from(Country country, Language language) {
        CountryLanguage countryLanguage = new CountryLanguage();
        countryLanguage.setId(new CountryLanguageId(country, language));
        return countryLanguage;
    }
}
