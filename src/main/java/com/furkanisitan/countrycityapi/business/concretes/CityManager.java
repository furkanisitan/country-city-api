package com.furkanisitan.countrycityapi.business.concretes;

import com.furkanisitan.countrycityapi.business.CityService;
import com.furkanisitan.countrycityapi.business.dtos.city.CityCreateDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityUpdateDto;
import com.furkanisitan.countrycityapi.dataaccess.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Service
public class CityManager implements CityService {

    private final CityRepository repository;

    @Autowired
    public CityManager(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CityDto> findAll() {
        return null;
    }

    @Override
    public CityDto findById(Long id) {
        return null;
    }

    @Override
    public CityDto create(CityCreateDto cityCreateDto) {
        return null;
    }

    @Override
    public void update(CityUpdateDto cityUpdateDto) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
