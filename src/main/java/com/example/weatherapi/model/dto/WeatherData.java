package com.example.weatherapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class WeatherData {
    private String cityName;
    private double temperature;
    private double feelsLikeTemperature;
    private double minTemperature;
    private double maxTemperature;
    private String description;
    private LocalDateTime timestamp;
}
