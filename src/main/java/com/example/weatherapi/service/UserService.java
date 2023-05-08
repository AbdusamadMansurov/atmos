package com.example.weatherapi.service;

import com.example.weatherapi.model.entity.Users;
import com.example.weatherapi.model.enums.Role;
import com.example.weatherapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public Flux<Users> getAll() {
        return userRepository.findAll();
    }

    public Mono<Users> getOne(Long id) {
        Mono<Users> usersMono = userRepository.findById(id);
        return usersMono.switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found ")));
    }


    public Mono<?> register(Users user) {
        Mono<Boolean> booleanMono = userRepository.existsByUsername(user.getUsername());
        if (Boolean.TRUE.equals(booleanMono.block())) {
            return Mono.just("There is a user with this username. Please enter another username");
        }
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Mono<ResponseEntity<?>> edit(Long id, Users user) {
        Mono<Boolean> booleanMono = userRepository.existsById(id);
        if (Boolean.FALSE.equals(booleanMono.block())) {
            return Mono.just(ResponseEntity.badRequest().body("There isn't a user with this id"));
        }
//        Flux<Users> usersFlux = userRepository.findAllByUsername(user.getUsername());
//        long count = usersFlux.toStream().count();
        Mono<Boolean> monoOptional = userRepository.findByUsernameAndIdNot(user.getUsername(), id);
        if (monoOptional.block().equals(Boolean.TRUE)) {
            return Mono.just(ResponseEntity.badRequest().body("There is a user with this username. Please enter another username"));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return Mono.just(ResponseEntity.ok(userRepository.save(user)));
    }

    public Mono<?> delete(Long id) {
        Mono<Boolean> booleanMono = userRepository.existsById(id);
        if (Boolean.FALSE.equals(booleanMono.block())) {
            return Mono.just(ResponseEntity.badRequest().body("There isn't a user with this id"));
        }

        Users user = userRepository.findById(id).block();
        user.setEnabled(!user.isEnabled());
        userRepository.save(user);
        return Mono.just(ResponseEntity.ok("Deleted"));
    }
}
