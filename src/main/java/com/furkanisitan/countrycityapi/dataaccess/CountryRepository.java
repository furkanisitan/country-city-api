package com.furkanisitan.countrycityapi.dataaccess;

import com.furkanisitan.countrycityapi.model.entities.Country;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @EntityGraph(attributePaths = {"cities", "countryLanguages"})
    @Query("select c from Country c where c.id=:id")
    Optional<Country> findByIdFetchAll(Long id);

    Optional<Country> getCountryByCode(String code);

    boolean existsByCode(String code);

}
