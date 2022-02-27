package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.countrycityapi.business.CountryService;
import com.furkanisitan.countrycityapi.business.mappers.CountryMapper;
import com.furkanisitan.countrycityapi.business.validators.CountryValidator;
import com.furkanisitan.countrycityapi.business.validators.LanguageValidator;
import com.furkanisitan.countrycityapi.dataaccess.CountryRepository;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CountryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@Primary
public class CountryManager implements CountryService {

    private final CountryRepository repository;
    private final CountryValidator validator;
    private final LanguageValidator languageValidator;

    @Autowired
    public CountryManager(CountryRepository repository, CountryValidator validator, LanguageValidator languageValidator) {
        this.repository = repository;
        this.validator = validator;
        this.languageValidator = languageValidator;
    }

    @Override
    public List<CountryResponse> findAll() {
        return CountryMapper.INSTANCE.toResponseList(repository.findAll());
    }

    @Override
    public CountryResponse findById(Long id) {
        return CountryMapper.INSTANCE.toResponse(repository.findById(id).orElse(null));
    }

    @Transactional
    @Override
    public CountryResponse create(CountryCreateRequest request) {

        validator.codeIsUnique(request.getCode());
        Country country = CountryMapper.INSTANCE.fromCreateRequest(request);

        for (var language : request.getLanguages()) {
            var proxyLanguage = languageValidator.getIfIdForeignKeyIsExists(language.getLanguageId());
            country.utility().addLanguage(proxyLanguage, language.isOfficial());
        }

        return CountryMapper.INSTANCE.toResponse(repository.save(country));
    }

    @Transactional
    @Override
    public void update(CountryUpdateRequest request) {

        Country country = validator.findIfIdIsExists(request.getId());
        CountryMapper.INSTANCE.updateFromUpdateRequest(request, country);

        // TODO delete and re-add all languages or update by checking
    }

    @Transactional
    @Override
    public void deleteById(Long id) {

    }

}
