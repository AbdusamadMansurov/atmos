package com.example.weatherapi.repository;

import com.example.weatherapi.model.entity.City;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface CityRepository extends ReactiveCrudRepository<City, Long> {
    Mono<Boolean> existsByName(String name);
    Mono<Boolean> existsByNameAndIdNot(String name, Long id);

    Flux<City> findAllByActiveTrue();
}
