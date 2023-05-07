package com.example.atmos.controller.authorization;

import com.example.atmos.model.Users;
import com.example.atmos.model.dto.LoginDTO;
import com.example.atmos.model.response.ApiResponse;
import com.example.atmos.security.JwtProvider;
import com.example.atmos.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author * Sunnatullayev Mahmudnazar *  * market *  * 14:58 *
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public Mono<?> login( @ModelAttribute LoginDTO request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = (UserDetails) authenticate.getPrincipal();
        String token = jwtProvider.generateToken(request.getUsername());
        return Mono.just(ApiResponse.builder()
                .success(true)
                .data(token)
                .message("Success authentication")
                .build());
    }
    @PostMapping("/register")
    public Mono<?> register(@RequestBody Users user) {
        return userService.register(user);
    }

}
