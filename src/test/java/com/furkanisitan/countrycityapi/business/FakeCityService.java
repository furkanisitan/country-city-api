package com.furkanisitan.countrycityapi.business;

import com.furkanisitan.countrycityapi.model.requests.CityCreateRequest;
import com.furkanisitan.countrycityapi.model.requests.CityUpdateRequest;
import com.furkanisitan.countrycityapi.model.responses.CityResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("fakeCityService")
class FakeCityService implements CityService {

    @Override
    public List<CityResponse> findAll() {
        return null;
    }

    @Override
    public CityResponse findById(Long id) {
        return null;
    }

    @Override
    public CityResponse create(CityCreateRequest request) {
        return null;
    }

    @Override
    public void update(CityUpdateRequest request) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
