package com.example.weatherapi.controller;

import com.example.weatherapi.model.entity.City;
import com.example.weatherapi.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;
    @GetMapping
    public Flux<City> getAll(){
        return cityService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<?> getOne(@PathVariable Long id){
        return cityService.getOne(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Mono<?> add(@Valid @RequestBody City city){
        return cityService.add(city);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Mono<?> edit(@PathVariable Long id,@Valid @RequestBody City city){
        return cityService.edit(id, city);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping
    public Mono<?> changeVisibility(@RequestParam Long id){
        return cityService.changeVisibility(id);
    }
}
