package com.furkanisitan.countrycityapi.dataaccess;

import com.furkanisitan.countrycityapi.model.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {

    List<City> findAllByCountryId(long countryId);

}

