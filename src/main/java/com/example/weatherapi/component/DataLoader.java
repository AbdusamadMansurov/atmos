package com.example.weatherapi.component;

import com.example.weatherapi.model.entity.Users;
import com.example.weatherapi.model.enums.Role;
import com.example.weatherapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${spring.sql.init.mode}")
    String mode;

    @Override
    public void run(String... args) {
        if (mode.equals("always")) {
            Users user = Users.builder()
                    .username("virus")
                    .fullName("Abdusamad Mansurov")
                    .password(passwordEncoder.encode("977515747"))
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(user);
        }
    }
}
