package com.example.atmos.service;

import com.example.atmos.model.User;
import com.example.atmos.model.dto.LoginDTO;
import com.example.atmos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public Flux<User> getAll() {
        return userRepository.findAll();
    }

    public Mono<User> getOne(Long id) {
//        Mono<User> userMono = userRepository.findById(id);
//        Mono<Boolean> booleanMono = userMono.hasElement();
//        if (booleanMono.blockOptional().isEmpty()){
//
//        }
        return userRepository.findById(id);
    }


    public Mono<?> register(User user) {
//        Mono<Boolean> booleanMono = userRepository.existsByUsername(user.getUsername());
//        if (Boolean.TRUE.equals(booleanMono.block())) {
//            return Mono.just("There is a user with this username. Please enter another username");
//        }
//
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).then();
    }

}
