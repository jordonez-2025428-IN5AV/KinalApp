package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.DetalleVenta;
import com.jeremyjuarez.kinalapp.entity.Productos;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {

    List<DetalleVenta> listarUsuarios();
    List<DetalleVenta> listarActivos();

    Productos guardar(DetalleVenta detalleVenta);

    Optional<DetalleVenta> buscarPorCodigoDV (String codigoDV);
    Productos actualizar(String codigoDV, DetalleVenta detalleVenta);
    void eliminar(String codigoDV);
    boolean existerPorCodigo (String codigoDV);
}
