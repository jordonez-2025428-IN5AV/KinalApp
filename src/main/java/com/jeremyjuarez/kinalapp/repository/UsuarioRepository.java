package com.jeremyjuarez.kinalapp.repository;

import com.jeremyjuarez.kinalapp.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    List<Usuario> findByEstado(int estado);

    Optional<Usuario> findByUsername(String username);
}
