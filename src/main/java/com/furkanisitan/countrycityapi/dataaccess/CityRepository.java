package com.furkanisitan.countrycityapi.dataaccess;

import com.furkanisitan.countrycityapi.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}

