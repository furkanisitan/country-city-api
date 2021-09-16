package com.furkanisitan.countrycityapi.entities;

import com.furkanisitan.countrycityapi.core.entities.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "languages")
@AttributeOverride(name = "id", column = @Column(name = "language_id", nullable = false))
public class Language extends Entity<Long> {

    @Column(unique = true, length = 10, nullable = false)
    private String code;

    @Column(length = 100, nullable = false)
    private String name;

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
        return code == null ? getClass().hashCode() : code.hashCode();
    }
    //endregion
}
