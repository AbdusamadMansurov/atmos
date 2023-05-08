package com.example.atmos.controller;

import com.example.atmos.model.entity.Subscription;
import com.example.atmos.model.entity.Users;
import com.example.atmos.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @GetMapping
    public Flux<Subscription> getAll(){
        return subscriptionService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Subscription> getOne(@PathVariable Long id){
        return subscriptionService.getOne(id);
    }

    @GetMapping("/getByUser")
    public Flux<Subscription> getAllByUser(@AuthenticationPrincipal Users user){
        return subscriptionService.getAllByUser(user);
    }

    @PostMapping
    public Mono<?> subscribe(@RequestParam Long city, @AuthenticationPrincipal Users user){
        return subscriptionService.subscribe(user, city);
    }

    @DeleteMapping
    public Mono<?> cancel(@RequestParam Long city, @AuthenticationPrincipal Users user){
        return subscriptionService.cancel(user, city);
    }
}
