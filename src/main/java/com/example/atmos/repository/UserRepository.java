package com.example.atmos.repository;

//import com.example.atmos.model.entity.Users;
import com.example.atmos.model.entity.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Repository
public interface UserRepository extends ReactiveCrudRepository<Users, Long> {

    Mono<Users> findByUsername(String username);

    Flux<Users> findAllByUsername(String username);
    Mono<Boolean> findByUsernameAndIdNot(String username, Long id);
    Mono<Boolean> existsByUsername(String username);
//    Mono<Optional<User>> findByUsername(String username);
}
