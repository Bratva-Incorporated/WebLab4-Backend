package com.porunit.l4.controllers;

import com.porunit.l4.data.UserDTO;
import com.porunit.l4.exceptions.UsernameTakenException;
import com.porunit.l4.jwt.JwtProvider;
import com.porunit.l4.repositories.UserRepository;
import com.porunit.l4.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(), userDTO.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token)
        );
    }

    @PostMapping("/register")
    @CrossOrigin(value = "*")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) throws UsernameTakenException {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UsernameTakenException("Username taken");
        }
        authService.register(userDTO);
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userDTO.getUsername(), userDTO.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @GetMapping("/username")
    public ResponseEntity<?> getUsernameFromToken(
            @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(authService.getUsernameFromHeader(authorizationHeader));
    }

}