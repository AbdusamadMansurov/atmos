package com.example.weatherapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherUpdateScheduler {

    private final WeatherService weatherService;

    @Autowired
    public WeatherUpdateScheduler(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Scheduled(fixedRate = 3600000) // 1 hour
    public void updateWeatherData() {
        weatherService.updateWeatherDataForAllCities();
    }

}
