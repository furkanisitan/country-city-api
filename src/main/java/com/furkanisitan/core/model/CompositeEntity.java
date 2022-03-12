package com.furkanisitan.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * Base entity class with embedded id.
 */

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class CompositeEntity<ID extends Serializable> implements Entity<ID> {

    @EmbeddedId
    protected ID id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeEntity<?> entity = (CompositeEntity<?>) o;
        return id != null && Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
