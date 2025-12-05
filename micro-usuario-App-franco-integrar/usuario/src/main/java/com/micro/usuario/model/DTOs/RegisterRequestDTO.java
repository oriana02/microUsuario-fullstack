package com.micro.usuario.model.DTOs;

import com.micro.usuario.model.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDTO {

    String nombre;
    String apellido;
    String email;
    String password;
    Role role;

}
