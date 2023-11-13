package com.example.saver.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.saver.component.JwtComponents;
import com.example.saver.dto.AppErrorDto;
import com.example.saver.dto.JwtRequestDto;
import com.example.saver.dto.JwtResponseDto;
import com.example.saver.service.UserService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtComponents jwtComponents;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDto authRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDto.password(), authRequestDto.password()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppErrorDto(HttpStatus.UNAUTHORIZED.value(), "Incorrect login or password"),
                    HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequestDto.username());
        String token = jwtComponents.generateToken(userDetails);
        return ResponseEntity.ok().body(new JwtResponseDto(token));
    }
}
