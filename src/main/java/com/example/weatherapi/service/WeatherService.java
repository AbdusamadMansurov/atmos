package com.example.weatherapi.service;

import com.example.weatherapi.model.entity.City;
import com.example.weatherapi.model.entity.Weather;
import com.example.weatherapi.repository.CityRepository;
import com.example.weatherapi.repository.WeatherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final WeatherRepository weatherRepository;
    private final WeatherApiClient weatherApiClient;
    private final CityRepository cityRepository;

    public Mono<Void> updateWeatherDataForAllCities() {
        Flux<City> cityFlux = cityRepository.findAllByActiveTrue();
        return cityFlux
                .flatMap(city -> {
                    Mono<Weather> weatherMono = weatherRepository.save(weatherApiClient.getWeatherData(city.getName()).block());
                    return weatherMono.map(weather -> {
                        city.setWeather(weather);
                        return city;
                    });
                })
                .flatMap(cityRepository::save).then();
    }


}
