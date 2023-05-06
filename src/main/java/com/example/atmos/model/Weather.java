package com.example.atmos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;
    private double temperature;
    private double feelsLikeTemperature;
    private double minTemperature;
    private double maxTemperature;
    private String description;
    private LocalDateTime timestamp;

}
