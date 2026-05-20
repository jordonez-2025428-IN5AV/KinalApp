package com.jeremyjuarez.kinalapp.configuration;

import com.jeremyjuarez.kinalapp.entity.Usuario;
import com.jeremyjuarez.kinalapp.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init() {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {

            Usuario u = new Usuario();
            u.setNombre("Jeremy");
            u.setCodigoUsuario("1");
            u.setUsername("admin");
            u.setEmail("admin@gmail.com");
            u.setPassword("123456");
            u.setRol("ADMIN");
            u.setEstado(1);

            usuarioRepository.save(u);

            System.out.println("Usuario admin creado");
        }
    }
}