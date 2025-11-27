package com.micro.usuario.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("api/v1")
public class UsuarioController {

    @PostMapping("admin/home")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminHome() {
        return ResponseEntity.ok("Bienvenido Admin");
    }

    @GetMapping("/user/home")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> userHome() {
        return ResponseEntity.ok("Bienvenido Usuario");
    }

}
