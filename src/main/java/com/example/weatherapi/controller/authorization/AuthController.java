package com.example.weatherapi.controller.authorization;

import com.example.weatherapi.model.entity.Users;
import com.example.weatherapi.model.dto.LoginDTO;
import com.example.weatherapi.model.response.ApiResponse;
import com.example.weatherapi.config.JwtUtil;
import com.example.weatherapi.service.UserService;
//import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public Mono<?> login( @ModelAttribute LoginDTO request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = (UserDetails) authenticate.getPrincipal();
        String token = jwtUtil.generateToken((Users) authenticate.getPrincipal());
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
