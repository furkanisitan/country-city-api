package com.furkanisitan.countrycityapi.model.entities;

import com.furkanisitan.core.model.CompositeEntity;
import com.furkanisitan.countrycityapi.model.entities.ids.CountryLanguageId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "country_languages")
public class CountryLanguage extends CompositeEntity<CountryLanguageId> {

    @Column(name = "is_official")
    private boolean isOfficial;

    public static CountryLanguage from(Country country, Language language) {
        CountryLanguage countryLanguage = new CountryLanguage();
        countryLanguage.setId(new CountryLanguageId(country, language));
        return countryLanguage;
    }
}
