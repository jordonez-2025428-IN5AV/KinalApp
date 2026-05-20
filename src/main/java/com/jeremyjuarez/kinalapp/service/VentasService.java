package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.Ventas;
import com.jeremyjuarez.kinalapp.repository.VentasRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VentasService implements IVentasService {

    private final VentasRepository ventasRepository;

    public VentasService(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<Ventas> listarVentas() {
        return ventasRepository.findAll();
    }

    @Override
    public List<Ventas> listarActivos() {
        return ventasRepository.findByEstado(1);
    }

    @Override
    public Optional<Ventas> buscarPorCodigo(String codigoVenta) {
        return ventasRepository.findById(String.valueOf(Integer.parseInt(codigoVenta)));
    }

    @Override
    public Ventas guardar(Ventas venta) {
        validar(venta);

        if (venta.getEstado() == 0) {
            venta.setEstado(1);
        }

        return ventasRepository.save(venta);
    }

    @Override
    public Ventas actualizar(String codigoVenta, Ventas venta) {
        Integer id = Integer.parseInt(codigoVenta);

        if (!ventasRepository.existsById(String.valueOf(id))) {
            throw new RuntimeException("Venta no encontrada: " + codigoVenta);
        }

        venta.setCodigoVenta(codigoVenta);
        validar(venta);

        return ventasRepository.save(venta);
    }

    @Override
    public void eliminar(String codigoVenta) {
        Integer id = Integer.parseInt(codigoVenta);

        if (!ventasRepository.existsById(String.valueOf(id))) {
            throw new RuntimeException("Venta no encontrada: " + codigoVenta);
        }

        ventasRepository.deleteById(String.valueOf(id));
    }

    @Override
    public boolean existePorCodigo(String codigoVenta) {
        return ventasRepository.existsById(String.valueOf(Integer.parseInt(codigoVenta)));
    }

    private void validar(Ventas venta) {
        if (venta.getCodigoVenta() == null) {
            throw new IllegalArgumentException("Código de venta obligatorio");
        }

        if (venta.getPrecioVenta() <= 0) {
            throw new IllegalArgumentException("Precio inválido");
        }
    }
}