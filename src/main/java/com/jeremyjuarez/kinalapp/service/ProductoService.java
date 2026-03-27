package com.jeremyjuarez.kinalapp.service;

import com.jeremyjuarez.kinalapp.entity.Productos;
import com.jeremyjuarez.kinalapp.entity.Usuario;
import com.jeremyjuarez.kinalapp.repository.ProductosRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class ProductoService implements IProductosService{
    private final ProductosRepository productosRepository;

    public ProductoService(ProductosRepository productosRepository) {
        this.productosRepository = productosRepository;
    }


    private void validarUsuario(Productos productos) {

        if (productos.getCodigoProducto() == null) {
            throw new IllegalArgumentException("El código de producto es obligatorio");
        }

        if (productos.getNombreProducto() == null || productos.getNombreProducto().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio");
        }

        if (productos.getPrecio() == 0 ) {
            throw new IllegalArgumentException("El precio es un dato obligatorio");
        }
    }

    @Override
    public List<Productos> listarUsuarios() {
        return productosRepository.findAll();
    }

    @Override
    public List<Productos> listarActivos() {
        return productosRepository.findByEstado(1);
    }

    @Override
    public Productos guardar(Productos productos) {
        validarUsuario(productos);
        if (productos.getEstado() == 0){
            productos.setEstado(1);
        }
        return productosRepository.save(productos);
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Productos> buscarPorCodigoP(String codigoP) {
        return productosRepository.findById(codigoP);
    }

    @Override
    public Productos actualizar(String codigoP, Productos productos) {
        if (!productosRepository.existsById(codigoP)){
            throw new RuntimeException("No se encontró ningún producto con el código: " + codigoP);
        }

        productos.setCodigoProducto(codigoP);
        validarUsuario(productos);

        return productosRepository.save(productos);
    }

    @Override
    public void eliminar(String codigo) {
        if (!productosRepository.existsById(codigo)){
            throw new RuntimeException("El producto no se encontró con el código: " + codigo);
        }
        productosRepository.deleteById(codigo);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existerPorCodigo(String codigo) {
        return productosRepository.existsById(codigo);
    }


}
