package com.porunit.l4.services;

import com.porunit.l4.repositories.UserRepository;
import com.porunit.l4.data.User;
import com.porunit.l4.data.UserDTO;
import com.porunit.l4.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public void register(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        userRepository.save(user);
    }

    public String getUsernameFromHeader(String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        return jwtProvider.getUsernameFromJwt(jwtToken);
    }
}