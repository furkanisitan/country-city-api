package com.furkanisitan.countrycityapi.dataaccess;

import com.furkanisitan.countrycityapi.model.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>, JpaSpecificationExecutor<Country> {
}
