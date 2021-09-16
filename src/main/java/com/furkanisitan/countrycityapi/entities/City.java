package com.furkanisitan.countrycityapi.entities;


import com.furkanisitan.countrycityapi.core.entities.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@javax.persistence.Entity
@Table(name = "cities")
@AttributeOverride(name = "id", column = @Column(name = "city_id", nullable = false))
public class City extends Entity<Long> {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private long population;

}
