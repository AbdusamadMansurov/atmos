package com.example.atmos.controller;

import com.example.atmos.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    @GetMapping
    public Mono<Void> updateWeather(){
        return weatherService.updateWeatherDataForAllCities();
    }
}
