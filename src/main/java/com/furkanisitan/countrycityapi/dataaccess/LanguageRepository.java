package com.furkanisitan.countrycityapi.dataaccess;

import com.furkanisitan.countrycityapi.model.entities.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
