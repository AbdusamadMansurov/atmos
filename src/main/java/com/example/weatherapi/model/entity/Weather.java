package com.example.weatherapi.model.entity;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Weather {
    private Long id;
    private String cityName;
    private double temperature;
    private double feelsLikeTemperature;
    private double minTemperature;
    private double maxTemperature;
    private String description;
    private LocalDateTime timestamp = LocalDateTime.now();
}
