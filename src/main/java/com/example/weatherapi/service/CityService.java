package com.example.weatherapi.service;

import com.example.weatherapi.model.entity.City;
import com.example.weatherapi.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public Flux<City> getAll() {
        return cityRepository.findAll();
    }

    public Mono<?> getOne(Long id) {
        if (cityRepository.existsById(id).block().equals(Boolean.FALSE)) {
            return Mono.just(ResponseEntity.badRequest().body("City not found"));
        }
        return cityRepository.findById(id);
    }


    public Mono<?> add(City city) {
        if (Boolean.TRUE.equals(cityRepository.existsByName(city.getName()).block())) {
            return Mono.just("There is a city with this name. Please enter another name");
        }
        return Mono.just(ResponseEntity.status(201).body(cityRepository.save(city)));
    }

    public Mono<?> edit(Long id, City city) {
        if (cityRepository.existsById(id).block().equals(Boolean.FALSE)) {
            return Mono.just("City not found");
        }
        if (Boolean.TRUE.equals(cityRepository.existsByNameAndIdNot(city.getName(), id).block())) {
            return Mono.just("There is a city with this name. Please enter another name");
        }
        City city1 = cityRepository.findById(id).block();
        city1.setActive(city.isActive());
        city1.setName(city.getName());
        city1.setDescription(city.getDescription());
        return Mono.just(ResponseEntity.status(200).body(cityRepository.save(city1)));
    }

    public Mono<?> changeVisibility(Long id) {
        if (cityRepository.existsById(id).block().equals(Boolean.FALSE)) {
            return Mono.just("City not found");
        }
        City city = cityRepository.findById(id).block();
        city.setActive(!city.isActive());
        return Mono.just(ResponseEntity.status(200).body(cityRepository.save(city)));
    }
}
