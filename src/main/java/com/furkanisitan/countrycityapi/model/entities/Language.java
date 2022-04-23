package com.furkanisitan.countrycityapi.model.entities;

import com.furkanisitan.core.model.BaseEntity;
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
@Table(name = "languages")
@AttributeOverride(name = "id", column = @Column(name = "language_id", nullable = false))
public class Language extends BaseEntity<Long> {

    @Column(unique = true, length = 10, nullable = false)
    private String code;

    @Column(length = 100, nullable = false)
    private String name;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.language", orphanRemoval = true)
    private Set<CountryLanguage> countryLanguages = new HashSet<>();

    //region equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return Objects.equals(code, language.code);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(code);
    }
    //endregion
}
