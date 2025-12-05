package com.micro.usuario.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.micro.usuario.model.AuthResponse;
import com.micro.usuario.model.DTOs.LoginRequestDTO;
import com.micro.usuario.model.DTOs.RegisterRequestDTO;
import com.micro.usuario.model.Role;
import com.micro.usuario.repository.UsuarioRepository;
import com.micro.usuario.model.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequestDTO request) {
        //verificar si el usuario ya existe
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya esta registrado");
        }

        User user;
        user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .role(request.getRole() != null ? request.getRole() : Role.USER)
                .build();
        usuarioRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

    public AuthResponse login(LoginRequestDTO request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()));
        UserDetails user = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse refreshToken(String refreshToken) {
        String email = jwtService.getEmailFromToken(refreshToken);
        UserDetails user = usuarioRepository.findByEmail(email).orElseThrow();

        if (jwtService.isTokenValid(refreshToken, user)) {
            String newToken = jwtService.getToken(user);
            return AuthResponse.builder()
                    .token(newToken)
                    .build();
        } else {
            throw new RuntimeException("Refresh token no valido");
        }
    }

}
