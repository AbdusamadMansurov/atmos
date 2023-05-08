package com.example.weatherapi.repository;

import com.example.weatherapi.model.entity.Subscription;
import com.example.weatherapi.model.entity.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SubscriptionRepository extends ReactiveCrudRepository<Subscription, Long> {
    Flux<Subscription> findAllByUserAndActiveTrueAndCity_ActiveTrue(Users user);
    Mono<Boolean> existsByUserAndCity_Id(Users user, Long city);
    Mono<Subscription> findByUserAndCity_Id(Users user, Long city);


}
