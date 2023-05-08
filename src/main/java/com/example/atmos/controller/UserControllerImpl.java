package com.example.atmos.controller;


import com.example.atmos.model.entity.Users;
import com.example.atmos.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserControllerImpl {

    private final UserService userService;

    @GetMapping
    public Flux<Users> getAll() {
        return userService.getAll();
    }

    //    @Override
    @GetMapping("/{id}")
    public Mono<Users> getOne(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @PutMapping("/{id}")
    public Mono<?> edit(@PathVariable Long id, @RequestBody Users user) {
        return userService.edit(id, user);
    }

    @DeleteMapping("/{id}")
    public Mono<?> delete(@PathVariable Long id){
        return userService.delete(id);
    }
}
