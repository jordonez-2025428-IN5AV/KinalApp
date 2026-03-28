package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.DetalleVenta;
import com.jeremyjuarez.kinalapp.repository.DetalleVentaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class DetalleVentaService implements IDetalleVentaService{

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @Override
    public List<DetalleVenta> listarUsuarios() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public List<DetalleVenta> listarActivos() {
        return detalleVentaRepository.findByEstado(1);
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public Optional<DetalleVenta> buscarPorCodigoDV(String codigoDV) {
        return detalleVentaRepository.findById(codigoDV);
    }

    @Override
    public DetalleVenta actualizar(String codigoDV, DetalleVenta detalleVenta) {
        if (!detalleVentaRepository.existsById(codigoDV)){
            throw new RuntimeException("No se encontró ningún producto con el código: " + codigoDV);
        }

        detalleVenta.setCodigoDetalleVenta(codigoDV);
        validarUsuario(detalleVenta);

        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void eliminar(String codigoDV) {
        if (!detalleVentaRepository.existsById(codigoDV)){
            throw new RuntimeException("El producto no se encontró con el código: " + codigoDV);
        }
        detalleVentaRepository.deleteById(codigoDV);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existerPorCodigo(String codigoDV) {
        return detalleVentaRepository.existsById(codigoDV);
    }

    private void validarUsuario(DetalleVenta detalleVenta) {

        if (detalleVenta.getCodigoDetalleVenta() == null) {
            throw new IllegalArgumentException("El código de producto es obligatorio");
        }

        if (detalleVenta.getPrecioTotal() == 0) {
            throw new IllegalArgumentException("El precio del producto es obligatorio");
        }

    }
}
