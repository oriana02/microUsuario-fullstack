package com.micro.usuario.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.usuario.model.User;
import com.micro.usuario.repository.UsuarioRepository;



@RestController
@RequestMapping("/debug/usuarios")
public class DebugController {

    private final UsuarioRepository usuarioRepository;

    public DebugController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<User> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}