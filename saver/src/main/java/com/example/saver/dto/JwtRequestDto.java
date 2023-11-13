package com.example.saver.dto;

import java.util.Objects;

public record JwtRequestDto(String username, String password) {
    public JwtRequestDto(String username, String password) {
        this.username = Objects.requireNonNull(username, "Username can't be null.");
        this.password = Objects.requireNonNull(password, "Password can't be null.");
    }
}
