package com.example.atmos.repository;

import com.example.atmos.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Repository
//@Qualifier
public interface UserRepository extends ReactiveCrudRepository<User, Long> {

//    Mono<User> findByUsername(String username);

    Flux<User> findAllByUsername(String username);
//    Mono<Boolean> existsByUsername(String username);
//    Mono<Optional<User>> findByUsername(String username);
}
