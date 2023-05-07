package com.example.atmos.service;

import com.example.atmos.model.Users;
import com.example.atmos.model.enums.Role;
import com.example.atmos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public Flux<Users> getAll() {
        return userRepository.findAll();
    }

    public Mono<ResponseEntity<Users>> getOne(Long id) {
//        Mono<User> userMono = userRepository.findById(id);
//        Mono<Boolean> booleanMono = userMono.hasElement();
//        if (booleanMono.blockOptional().isEmpty()){
//
//        }
        return Mono.just(ResponseEntity.ok(userRepository.findById(id).block()));
    }


    public Mono<?> register(Users user) {
//        Mono<Boolean> booleanMono = userRepository.existsByUsername(user.getUsername());
//        if (Boolean.TRUE.equals(booleanMono.block())) {
//            return Mono.just("There is a user with this username. Please enter another username");
//        }
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
