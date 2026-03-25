package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.Productos;
import com.jeremyjuarez.kinalapp.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IProductosService {
    List<Productos> listarUsuarios();
    List<Productos> listarActivos();

    Usuario guardar(Usuario usuario);

    Optional<Productos> buscarPorCodigoP (String codigoP);
    Productos actualizar(String codigoP, Usuario usuario);
    void eliminar(String codigo);
    boolean existerPorCodigo (String codigo);
}
