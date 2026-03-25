package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    List<Usuario> listarUsuarios();
    List<Usuario> listarActivos();

    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorCodigo (String codigo);
    Usuario actualizar(String codigo, Usuario usuario);
    void eliminar(String codigo);
    boolean existePorCodigo (String codigo);
}
