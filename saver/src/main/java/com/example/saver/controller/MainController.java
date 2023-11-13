package com.example.saver.controller;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/secured")
    public String securedData() {
        return "Secured data";
    }

    @GetMapping("/unsecured")
    public String unsecuredData() {
        return "Unsecured data";
    }

    @GetMapping("/admin")
    public String adminData() {
        return "Admin data";
    }

    @GetMapping("/info")
    public String infoData(Principal principal) {
        return principal.getName();
    }
}
