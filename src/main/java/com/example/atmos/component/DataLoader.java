package com.example.atmos.component;

import com.example.atmos.model.User;
import com.example.atmos.model.enums.Role;
import com.example.atmos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author * Sunnatullayev Mahmudnazar *  * market *  * 15:36 *
 */

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
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ADMIN);

            User user = User.builder()
                    .username("virus")
                    .fullName("Abdusamad Mansurov")
                    .password(passwordEncoder.encode("977515747"))
//                    .company(save)
                    .roles(roles)
                    .build();

            userRepository.save(user);
        }
    }
}
