package com.example.atmos.service;

import com.example.atmos.model.City;
import com.example.atmos.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public Flux<City> getAll() {
        return cityRepository.findAll();
    }
}
