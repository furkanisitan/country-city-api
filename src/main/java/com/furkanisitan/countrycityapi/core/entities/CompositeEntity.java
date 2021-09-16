package com.furkanisitan.countrycityapi.core.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * @param <ID> the type of the embedded id of entity.
 */

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class CompositeEntity<ID extends Serializable> {

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
