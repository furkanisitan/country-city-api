package com.furkanisitan.countrycityapi.business;

import com.furkanisitan.countrycityapi.business.dtos.city.CityCreateDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityDto;
import com.furkanisitan.countrycityapi.business.dtos.city.CityUpdateDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("fakeCityService")
class FakeCityService implements CityService {

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
