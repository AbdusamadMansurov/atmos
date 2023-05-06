//package com.example.atmos.service.authorization;
//
//import com.bukhari.market.model.dto.response.ApiResponse;
//import com.bukhari.market.model.entity.User;
//import com.bukhari.market.model.enums.Role;
//import com.bukhari.market.repository.UserRepository;
//import com.bukhari.market.security.JwtProvider;
//import lombok.RequiredArgsConstructor;
//import lombok.SneakyThrows;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.nio.file.AccessDeniedException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
///**
// * @author * Sunnatullayev Mahmudnazar *  * market *  * 15:54 *
// */
//
//@Service
//@RequiredArgsConstructor
//public class AuthService implements UserDetailsService {
//    private final UserRepository userRepository;
//    private final JwtProvider jwtProvider;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//
//        return optionalUser.orElse(null);
//    }
//
////    @SneakyThrows
////    public ApiResponse<?> changeRole(User user, String role) {
////        if (user == null) {
////            throw new AccessDeniedException("");
////        }
////        for (Role userRole : user.getRoles()) {
////            if (userRole.toString().equals(role)) {
////                Map<String, Object> claims = new HashMap<>();
////                claims.put("role", Role.valueOf(role));
////
////                return ApiResponse.builder()
////                        .status(200)
////                        .message("Success")
////                        .data(jwtProvider.generateToken(user, claims))
////                        .success(true)
////                        .build();
////            }
////        }
////        throw new AccessDeniedException("");
////    }
//
//}
