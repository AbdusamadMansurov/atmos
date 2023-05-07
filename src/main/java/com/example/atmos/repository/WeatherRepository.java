package com.example.atmos.repository;

import com.example.atmos.model.City;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends ReactiveCrudRepository<City, Long> {
}
