package com.furkanisitan.countrycityapi.dataaccess;

import com.furkanisitan.countrycityapi.model.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> getCountryByCode(String code);

    boolean existsByCode(String code);

}
