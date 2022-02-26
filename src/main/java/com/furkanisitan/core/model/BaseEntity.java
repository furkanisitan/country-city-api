package com.furkanisitan.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Base entity class.
 *
 * @param <ID> the type of the entity's id.
 */

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Entity<ID> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected ID id;

    /**
     * @implNote If a control for subclasses is desired, this method should be overridden.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity<?> abstractEntity = (BaseEntity<?>) o;
        return id != null && Objects.equals(id, abstractEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}