package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.Ventas;

import java.util.List;
import java.util.Optional;

public interface IVentasService {

    List<Ventas> listarVentas();

    List<Ventas> listarActivos();

    Optional<Ventas> buscarPorCodigo(String codigoVenta);

    Ventas guardar(Ventas venta);

    Ventas actualizar(String codigoVenta, Ventas venta);

    void eliminar(String codigoVenta);

    boolean existePorCodigo(String codigoVenta);
}