package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface IProductosService {
    List<Productos> listarUsuarios();
    List<Productos> listarActivos();

    Productos guardar(Productos productos);

    Optional<Productos> buscarPorCodigoP (String codigoP);
    Productos actualizar(String codigoP, Productos productos);
    void eliminar(String codigo);
    boolean existerPorCodigo (String codigo);
}
