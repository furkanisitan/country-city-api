package com.furkanisitan.countrycityapi.dataaccess;

import com.furkanisitan.core.dataaccess.SpecificationRepository;
import com.furkanisitan.countrycityapi.model.entities.Language;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends SpecificationRepository<Language, Long> {
}
