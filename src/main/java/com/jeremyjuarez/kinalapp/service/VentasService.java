package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.Ventas;
import com.jeremyjuarez.kinalapp.repository.VentasRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class VentasService implements IVentasService{

    private final VentasRepository  ventasRepository;

    public VentasService(VentasRepository ventasRepository) {
        this.ventasRepository = ventasRepository;
    }

    @Override
    public List<Ventas> listarUsuarios() {
        return ventasRepository.findAll();
    }

    @Override
    public List<Ventas> listarActivos() {
        return ventasRepository.findByEstado(1);
    }

    @Override
    public Ventas guardar(Ventas ventas) {
        validarVentas(ventas);
        if (ventas.getEstado() == 0){
            ventas.setEstado(1);
        }
        return ventasRepository.save(ventas);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ventas> buscarPorCodigoV(String codigoVenta) {
        return ventasRepository.findById(Integer.valueOf(codigoVenta));
    }

    @Override
    public Ventas actualizar(String codigoVenta, Ventas ventas) {
        if (!ventasRepository.existsById(Integer.valueOf(codigoVenta))){
            throw new RuntimeException("No se encontró ningún producto con el código: " + codigoVenta);
        }

        ventas.setCodigoVenta(codigoVenta);
        validarVentas(ventas);

        return ventasRepository.save(ventas);
    }

    @Override
    public void eliminar(String codigoVenta) {
        if (!ventasRepository.existsById(Integer.valueOf(codigoVenta))){
            throw new RuntimeException("El producto no se encontró con el código: " + codigoVenta);
        }
        ventasRepository.deleteById(Integer.valueOf(codigoVenta));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorCodigoV(String codigoVenta) {
        return ventasRepository.existsById(Integer.valueOf(codigoVenta));
    }

    private void validarVentas(Ventas ventas) {
        if (ventas.getCodigoVenta() == null) {
            throw new IllegalArgumentException("El codigo de venta no puede ser nulo");
        }

        if (ventas.getPrecioVenta() == 0) {
            throw new IllegalArgumentException("El precio de venta no puede ser 0");
        }
    }
}
