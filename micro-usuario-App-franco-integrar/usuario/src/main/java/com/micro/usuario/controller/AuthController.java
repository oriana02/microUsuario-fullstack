package com.micro.usuario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.micro.usuario.model.AuthResponse;
import com.micro.usuario.model.DTOs.LoginRequestDTO;
import com.micro.usuario.model.DTOs.RegisterRequestDTO;
import com.micro.usuario.service.AuthService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequestDTO request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@Validated @RequestBody RegisterRequestDTO request) {

        return ResponseEntity.ok(authService.register(request));
    }

}
