package com.jeremyjuarez.kinalapp.config;

import com.jeremyjuarez.kinalapp.entity.Usuario;
import com.jeremyjuarez.kinalapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {

            Usuario u = new Usuario();
            u.setCodigoUsuario("1");
            u.setUsername("admin");
            u.setPassword(passwordEncoder.encode("123456")); // 🔐 clave real
            u.setRol("ADMIN");
            u.setEstado(1);

            usuarioRepository.save(u);

            System.out.println("✅ Usuario admin creado");
        }
    }
}