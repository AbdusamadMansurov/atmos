package com.example.atmos.controller;

import com.example.atmos.model.City;
import com.example.atmos.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    @GetMapping
    public Flux<City> getAll(){
        return cityService.getAll();
    }
}
