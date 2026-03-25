package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.Usuario;
import com.jeremyjuarez.kinalapp.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class UsuarioService implements IUsuarioService{

    private final UsuarioRepository usuarioR;

    public UsuarioService(UsuarioRepository usuarioR) {
        this.usuarioR = usuarioR;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios() {
        return usuarioR.findAll();
    }

    @Override
    public List<Usuario> listarActivos() {
        return usuarioR.findByEstado(1);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        validarUsuario(usuario);
        if (usuario.getEstado() == 0){
            usuario.setEstado(1);
        }
        return usuarioR.save(usuario);
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Usuario> buscarPorCodigo(String codigo) {
        return usuarioR.findById(codigo);
    }

    @Override
    public Usuario actualizar(String codigo, Usuario usuario) {
        if (!usuarioR.existsById(codigo)){
            throw new RuntimeException("No se encontró ningún usuario con el código: " + codigo);
        }

        usuario.setCodigoUsuario(codigo);
        validarUsuario(usuario);

        return usuarioR.save(usuario);
    }

    @Override
    public void eliminar(String codigo) {
        if (!usuarioR.existsById(codigo)){
            throw new RuntimeException("El cliente no se encontró con el código: " + codigo);
        }
        usuarioR.deleteById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigo(String codigo) {
        return usuarioR.existsById(codigo);
    }

    private void validarUsuario(Usuario usuario) {

        if (usuario.getCodigoUsuario() == null || usuario.getCodigoUsuario().trim().isEmpty()) {
            throw new IllegalArgumentException("El código de usuario es obligatorio");
        }

        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de usuario es obligatorio");
        }

        if (usuario.getPassword() == null || usuario.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("La contraseña es obligatoria");
        }

        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
    }

}
