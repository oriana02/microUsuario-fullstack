package com.micro.usuario.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.micro.usuario.model.User;

public interface UsuarioRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNombre(String nombre);

}
