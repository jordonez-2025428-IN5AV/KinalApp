package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.Ventas;

import java.util.List;
import java.util.Optional;

public interface IVentasService {
    List<Ventas> listarUsuarios();
    List<Ventas> listarActivos();

    Ventas guardar(Ventas ventas);

    Optional<Ventas> buscarPorCodigoV(String codigoVenta);
    Ventas actualizar(String codigoVenta, Ventas ventas);
    void eliminar(String codigoVenta);
    boolean existePorCodigoV(String codigoVenta);
}
