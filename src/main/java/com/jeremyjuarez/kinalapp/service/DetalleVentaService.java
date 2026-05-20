package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.DetalleVenta;
import com.jeremyjuarez.kinalapp.repository.DetalleVentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService implements IDetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> listarDetalles() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public Optional<DetalleVenta> buscarPorId(String id) {
        return detalleVentaRepository.findById(id);
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalle) {
        return detalleVentaRepository.save(detalle);
    }

    @Override
    public void eliminar(String id) {
        detalleVentaRepository.deleteById(id);
    }
}