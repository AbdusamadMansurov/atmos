package com.example.atmos.service;

import com.example.atmos.model.entity.City;
import com.example.atmos.model.entity.Subscription;
import com.example.atmos.model.entity.Users;
import com.example.atmos.repository.CityRepository;
import com.example.atmos.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final CityRepository cityRepository;

    public Flux<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    public Mono<Subscription> getOne(Long id) {
        return subscriptionRepository.findById(id);
    }

    public Flux<Subscription> getAllByUser(Users user) {
        return subscriptionRepository.findAllByUserAndActiveTrueAndCity_ActiveTrue(user);
    }

    public Mono<?> subscribe(Users user, Long city) {
        if (Boolean.FALSE.equals(cityRepository.existsById(city).block()) || !cityRepository.findById(city).block().isActive()) {
            return Mono.just(ResponseEntity.badRequest().body("City cot found"));
        }
        City cityBlock = cityRepository.findById(city).block();
        if (cityBlock.isActive()) {
            return Mono.just(ResponseEntity.badRequest().body("City cot found"));
        }
        if (Boolean.TRUE.equals(subscriptionRepository.existsByUserAndCity_Id(user, city).block())) {
            Mono<Subscription> subscriptionMono = subscriptionRepository.findByUserAndCity_Id(user, city);
            Subscription subscription = subscriptionMono.block();
            subscription.setActive(true);
            return Mono.just(ResponseEntity.ok().body(subscriptionRepository.save(subscription)));
        }

        Subscription subscription = new Subscription();
        subscription.setCity(cityBlock);
        subscription.setUser(user);
        subscription.setActive(true);
        return Mono.just(ResponseEntity.status(201).body(subscriptionRepository.save(subscription)));
    }

    public Mono<?> cancel(Users user, Long city) {
        if (Boolean.TRUE.equals(subscriptionRepository.existsByUserAndCity_Id(user, city).block())) {
            Mono<Subscription> subscriptionMono = subscriptionRepository.findByUserAndCity_Id(user, city);
            Subscription subscription = subscriptionMono.block();
            subscription.setActive(false);
            return Mono.just(ResponseEntity.ok().body(subscriptionRepository.save(subscription)));
        } else {
            return Mono.just(ResponseEntity.badRequest().body("Subscription not found"));
        }
    }
}
