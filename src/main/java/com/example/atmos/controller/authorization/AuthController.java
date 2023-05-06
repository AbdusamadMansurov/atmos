package com.example.atmos.controller.authorization;

import com.example.atmos.model.dto.LoginDTO;
import com.example.atmos.model.response.ApiResponse;
import com.example.atmos.security.JwtProvider;
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

    @PostMapping("/login")
    public Mono<?> login(@Valid @RequestBody LoginDTO request) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = (UserDetails) authenticate.getPrincipal();
        String token = jwtProvider.generateToken(request.getUsername());
        return Mono.just(ApiResponse.builder()
                .success(true)
                .data(token)
                .message("Success authentication")
                .build());
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", user.getAuthorities().stream().toList().get(0).getAuthority());

//        return ResponseEntity.ok().body(ApiResponse.builder()
//                .success(true)
//                .data(jwtProvider.generateToken(user, claims))
//                .message("Success authentication")
//                .build());
    }

//    @PatchMapping("/role")
//    public ResponseEntity<?> changeRole(@AuthenticationPrincipal User user, @RequestParam String role) {
//        ApiResponse<?> response = authService.changeRole(user, role);
//        return ResponseEntity.status(response.getStatus()).body(response);
//    }

}
