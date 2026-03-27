package com.jeremyjuarez.kinalapp.controller;

import com.jeremyjuarez.kinalapp.entity.Productos;
import com.jeremyjuarez.kinalapp.entity.Usuario;
import com.jeremyjuarez.kinalapp.service.IProductosService;
import com.jeremyjuarez.kinalapp.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping
@RestController("/productos")

public class ProductoController {
    private final IProductosService productosService;

    public ProductoController(IProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public ResponseEntity<List<Productos>> listar(){
        List<Productos> productos = productosService.listarUsuarios();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Productos> buscarPorDPI(@PathVariable String codigo){
        return productosService.buscarPorCodigoP(codigo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Productos productos){
        try {
            Productos nuevoProducto = productosService.guardar(productos);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo){
        try {
            if (!productosService.existerPorCodigo(codigo)){
                return ResponseEntity.notFound().build();
            }
            productosService.eliminar(codigo);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable String codigoP, @RequestBody Productos productos){
        try {
            if (!productosService.existerPorCodigo(codigoP)){
                return ResponseEntity.notFound().build();
            }
            Productos usuarioActualizado = productosService.actualizar(codigoP, productos);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Productos>> listarActivos(){
        List<Productos> ProductosA = productosService.listarActivos();
        return ResponseEntity.ok(ProductosA);
    }
}
