package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {

    List<DetalleVenta> listarDetalles();

    Optional<DetalleVenta> buscarPorId(Integer id);

    DetalleVenta guardar(DetalleVenta detalle);

    void eliminar(Integer id);
}