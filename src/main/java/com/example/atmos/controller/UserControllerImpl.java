package com.example.atmos.controller;

import com.example.atmos.controller.interfaces.UserController;
import com.example.atmos.model.User;
import com.example.atmos.model.dto.LoginDTO;
import com.example.atmos.repository.UserRepository;
import com.example.atmos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    @GetMapping
    public Flux<User> getAll() {
        return userService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Mono<User> getOne(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @Override
    @PostMapping("/register")
    public Mono<?> register(@RequestBody User user) {
        return userService.register(user);
    }

}
