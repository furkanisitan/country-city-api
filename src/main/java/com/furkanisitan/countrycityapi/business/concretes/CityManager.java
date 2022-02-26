package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.core.exceptions.ForeignKeyConstraintException;
import com.furkanisitan.core.exceptions.RecordNotFoundException;
import com.furkanisitan.countrycityapi.business.CityService;
import com.furkanisitan.countrycityapi.business.CountryService;
import com.furkanisitan.countrycityapi.business.mappers.CityMapper;
import com.furkanisitan.countrycityapi.dataaccess.CityRepository;
import com.furkanisitan.countrycityapi.model.entities.City;
import com.furkanisitan.countrycityapi.model.entities.Country;
import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CityUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CityResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
@Primary
public class CityManager implements CityService {

    private final CityRepository repository;
    private final CountryService countryService;

    @Autowired
    public CityManager(CityRepository repository, CountryService countryService) {
        this.repository = repository;
        this.countryService = countryService;
    }

    @Override
    public List<CityResponse> findAll() {
        return CityMapper.INSTANCE.toResponseList(repository.findAll());
    }

    @Override
    public CityResponse findById(Long id) {
        return CityMapper.INSTANCE.toResponse(repository.findById(id).orElse(null));
    }

    @Transactional
    @Override
    public CityResponse create(CityCreateRequest request) {

        // Check if there is a country with the foreign key 'countryCode'
        Country country = countryService.getByCode(request.getCountryCode());
        if (country == null)
            throw new ForeignKeyConstraintException("countryCode", request.getCountryCode());

        City city = CityMapper.INSTANCE.fromCreateRequest(request);
        city.setCountry(country);

        return CityMapper.INSTANCE.toResponse(repository.save(city));
    }

    @Transactional
    @Override
    public void update(CityUpdateRequest request) {

        City city = repository.findById(request.getId())
                // Check if the city is exists
                .orElseThrow(() -> new RecordNotFoundException(City.class.getSimpleName(), Pair.of("id", request.getId())));

        // Check if there is a country with the foreign key 'countryCode'
        Country country = countryService.getByCode(request.getCountryCode());
        if (country == null)
            throw new ForeignKeyConstraintException("countryCode", request.getCountryCode());

        CityMapper.INSTANCE.updateFromUpdateRequest(request, city);
        city.setCountry(country);

        repository.save(city);
    }

    @Override
    public void deleteById(Long id) {

        if (!repository.existsById(id))
            throw new RecordNotFoundException(City.class.getSimpleName(), Pair.of("id", id));

        repository.deleteById(id);
    }
}
