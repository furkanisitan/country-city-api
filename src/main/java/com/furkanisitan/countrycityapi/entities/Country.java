package com.furkanisitan.countrycityapi.entities;

import com.furkanisitan.countrycityapi.core.entities.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "countries")
@AttributeOverride(name = "id", column = @Column(name = "country_id", nullable = false))
public class Country extends Entity<Long> {

    @Column(unique = true, length = 5, nullable = false)
    private String code;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "life_expectancy", scale = 1)
    private double lifeExpectancy;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    private Set<City> cities = new HashSet<>();

    @OneToMany(mappedBy = "country", orphanRemoval = true)
    private Set<CountryLanguage> countryLanguages = new HashSet<>();

    //region equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(code, country.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
    //endregion

}
