package com.example.atmos.service;

import com.example.atmos.model.entity.Weather;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class WeatherApiClient {
    private final WebClient webClient;

    public WeatherApiClient() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .build();
    }

    public Mono<Weather> getWeatherData(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/weather")
                        .queryParam("q", city)
                        .queryParam("cnt", 7)
                        .queryParam("appid", "cc36e60e0384107b7c7513fd23ef0c7f")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Weather.class);
    }
}
