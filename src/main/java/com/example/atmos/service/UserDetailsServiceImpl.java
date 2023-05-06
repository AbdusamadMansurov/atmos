package com.example.atmos.service;

import com.example.atmos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

//    private final UserRepository userRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
//        return userRepository.findByUsername(username)
//                .map(user -> (UserDetails) user)
//                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User not found with username: " + username)));
        return Mono.empty();
    }
}
