package com.example.atmos.controller.interfaces;

import com.example.atmos.model.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping("/city")
public interface UserController {

    @GetMapping
    Flux<User> getAll();

    @GetMapping("/{id}")
    Mono<User> getOne(@PathVariable Long id);

    @PostMapping
    Mono<?> register(@RequestBody User user);

//    @GetMapping("/{id}")

}
