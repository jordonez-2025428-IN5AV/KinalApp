package com.jeremyjuarez.kinalapp.controller;

import com.jeremyjuarez.kinalapp.entity.DetalleVenta;
import com.jeremyjuarez.kinalapp.entity.Ventas;
import com.jeremyjuarez.kinalapp.service.IDetalleVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DetalleVentaController {
    private final IDetalleVentaService detalleVentaService;

    public DetalleVentaController(IDetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }


    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar(){
        List<DetalleVenta> usuario = detalleVentaService.listarUsuarios();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<DetalleVenta> buscarPorDPI(@PathVariable String codigo){
        return detalleVentaService.buscarPorCodigoDV(codigo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody DetalleVenta detalleVenta ){
        try {
            DetalleVenta nuevoDetalle = detalleVentaService.guardar(detalleVenta);
            return new ResponseEntity<>(nuevoDetalle, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo){
        try {
            if (!detalleVentaService.existerPorCodigo(codigo)){
                return ResponseEntity.notFound().build();
            }
            detalleVentaService.eliminar(codigo);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable String codigo, @RequestBody DetalleVenta detalleVenta){
        try {
            if (!detalleVentaService.existerPorCodigo(codigo)){
                return ResponseEntity.notFound().build();
            }
            DetalleVenta usuarioActualizado = detalleVentaService.actualizar(codigo, detalleVenta);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<List<DetalleVenta>> listarActivos(){
        List<DetalleVenta> UsuariosA = detalleVentaService.listarActivos();
        return ResponseEntity.ok(UsuariosA);
    }
}
