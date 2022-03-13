package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.countrycityapi.business.CountryService;
import com.furkanisitan.countrycityapi.business.mappers.CountryMapper;
import com.furkanisitan.countrycityapi.business.validators.CountryValidator;
import com.furkanisitan.countrycityapi.business.validators.LanguageValidator;
import com.furkanisitan.countrycityapi.dataaccess.CountryRepository;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.entities.Country_;
import com.furkanisitan.countrycityapi.model.entities.Language_;
import com.furkanisitan.countrycityapi.model.requests.CountryCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CountryUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CountryCreateResponse;
import com.furkanisitan.countrycityapi.model.responses.CountryListResponse;
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
    public List<CountryListResponse> findAll() {
        return CountryMapper.INSTANCE.toResponseList(repository.findAll());
    }

    @Override
    public CountryResponse findById(Long id) {
        return CountryMapper.INSTANCE.toResponse(repository.findById(id).orElse(null));
    }

    @Transactional
    @Override
    public CountryCreateResponse create(CountryCreateRequest request) {

        validator.uniqueBy(Country_.CODE, request.getCode());
        Country country = CountryMapper.INSTANCE.fromCreateRequest(request);

        for (var countryLanguage : request.getLanguages()) {
            var language = languageValidator.findForeignBy(Language_.ID, countryLanguage.getLanguageId());
            country.utility().addLanguage(language, countryLanguage.isOfficial());
        }

        return CountryMapper.INSTANCE.toCreateResponse(repository.save(country));
    }

    @Transactional
    @Override
    public void update(CountryUpdateRequest request) {

        Country country = validator.findBy(Country_.ID, request.getId());
        validator.uniqueBy(Country_.CODE, request.getCode(), request.getId());

        CountryMapper.INSTANCE.updateFromUpdateRequest(request, country);

        country.utility().clearLanguages();
        for (var countryLanguage : request.getLanguages()) {
            var language = languageValidator.findForeignBy(Language_.ID, countryLanguage.getLanguageId());
            country.utility().addLanguage(language, countryLanguage.isOfficial());
        }

        repository.save(country);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        validator.existsBy(Country_.ID, id);
        repository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}
