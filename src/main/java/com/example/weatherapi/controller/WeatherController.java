package com.example.weatherapi.controller;

import com.example.weatherapi.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class WeatherController {

    private final WeatherService weatherService;
    @GetMapping
    public Mono<Void> updateWeather(){
        return weatherService.updateWeatherDataForAllCities();
    }
}
