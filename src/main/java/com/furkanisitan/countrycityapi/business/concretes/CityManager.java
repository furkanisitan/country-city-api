package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.countrycityapi.business.CityService;
import com.furkanisitan.countrycityapi.business.CountryService;
import com.furkanisitan.countrycityapi.business.dtos.city.CityCreateDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityUpdateDto;
import com.furkanisitan.countrycityapi.business.mappers.CityMapper;
import com.furkanisitan.countrycityapi.core.exceptions.EntityNotExistsException;
import com.furkanisitan.countrycityapi.core.exceptions.ForeignKeyConstraintViolationException;
import com.furkanisitan.countrycityapi.dataaccess.CityRepository;
import com.furkanisitan.countrycityapi.entities.City;
import com.furkanisitan.countrycityapi.entities.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class CityManager implements CityService {

    private final CityRepository repository;
    private final CountryService countryService;

    @Autowired
    public CityManager(CityRepository repository, CountryService countryService) {
        this.repository = repository;
        this.countryService = countryService;
    }

    @Override
    public List<CityDto> findAll() {
        return CityMapper.INSTANCE.toCityDtoList(repository.findAll());
    }

    @Override
    public CityDto findById(Long id) {
        return CityMapper.INSTANCE.toCityDto(repository.findById(id).orElse(null));
    }

    @Override
    public CityDto create(CityCreateDto cityCreateDto) {

        // Check if there is a country with the foreign key 'countryCode'
        Country country = countryService.getByCode(cityCreateDto.getCountryCode());
        if (country == null)
            throw new ForeignKeyConstraintViolationException("countryCode", cityCreateDto.getCountryCode());

        City city = CityMapper.INSTANCE.fromCityCreateDto(cityCreateDto);
        city.setCountry(country);

        return CityMapper.INSTANCE.toCityDto(repository.save(city));
    }

    @Override
    public void update(CityUpdateDto cityUpdateDto) {

        City city = repository.findById(cityUpdateDto.getId())
                // Check if the city is exists
                .orElseThrow(() -> new EntityNotExistsException(City.class.getSimpleName(), Pair.of("id", cityUpdateDto.getId())));


        // Check if there is a country with the foreign key 'countryCode'
        Country country = countryService.getByCode(cityUpdateDto.getCountryCode());
        if (country == null)
            throw new ForeignKeyConstraintViolationException("countryCode", cityUpdateDto.getCountryCode());

        CityMapper.INSTANCE.updateFromCityUpdateDto(cityUpdateDto, city);
        city.setCountry(country);

        repository.save(city);
    }

    @Override
    public void deleteById(Long id) {

        if (!repository.existsById(id))
            throw new EntityNotExistsException(City.class.getSimpleName(), Pair.of("id", id));

        repository.deleteById(id);
    }
}
