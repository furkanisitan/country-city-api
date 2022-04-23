package com.furkanisitan.countrycityapi.model.entities;

import com.furkanisitan.core.model.BaseEntity;
import com.furkanisitan.core.model.utility.HasUtility;
import com.furkanisitan.countrycityapi.model.entities.enums.Continent;
import com.furkanisitan.countrycityapi.model.entities.utilities.CountryUtility;
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
@Entity
@Table(name = "countries")
@AttributeOverride(name = "id", column = @Column(name = "country_id", nullable = false))
public class Country extends BaseEntity<Long> implements HasUtility<CountryUtility> {

    @Column(unique = true, length = 5, nullable = false)
    private String code;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name = "life_expectancy", scale = 1)
    private double lifeExpectancy;

    @Enumerated
    @Column(nullable = false, columnDefinition = "TINYINT")
    private Continent continent;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "country", orphanRemoval = true)
    private Set<City> cities = new HashSet<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.country", orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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

    @Override
    public CountryUtility utility() {
        return new CountryUtility(this);
    }

}
