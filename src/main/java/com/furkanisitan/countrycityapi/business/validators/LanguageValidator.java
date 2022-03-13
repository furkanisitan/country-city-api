package com.furkanisitan.countrycityapi.business.validators;

import com.furkanisitan.core.validation.Validator;
import com.furkanisitan.countrycityapi.dataaccess.LanguageRepository;
import com.furkanisitan.countrycityapi.model.entities.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LanguageValidator extends Validator<Language, Long> {


    @Autowired
    public LanguageValidator(LanguageRepository repository) {
        super(Language.class, repository);
    }

}
