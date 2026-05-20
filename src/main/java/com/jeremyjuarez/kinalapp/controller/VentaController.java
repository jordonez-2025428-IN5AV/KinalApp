package com.jeremyjuarez.kinalapp.controller;

import com.jeremyjuarez.kinalapp.entity.Ventas;
import com.jeremyjuarez.kinalapp.service.IVentasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")

public class VentaController {

    private final IVentasService ventasService;

    public VentaController(IVentasService ventasService) {
        this.ventasService = ventasService;
    }

    @GetMapping
    public ResponseEntity<List<Ventas>> listar(){
        List<Ventas> usuario = ventasService.listarVentas();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Ventas> buscarPorDPI(@PathVariable String codigo){
        return ventasService.buscarPorCodigo(codigo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Ventas usuario ){
        try {
            Ventas nuevoUsuario = ventasService.guardar(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo){
        try {
            if (!ventasService.existePorCodigo(codigo)){
                return ResponseEntity.notFound().build();
            }
            ventasService.eliminar(codigo);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable String codigo, @RequestBody Ventas usuario){
        try {
            if (!ventasService.existePorCodigo(codigo)){
                return ResponseEntity.notFound().build();
            }
            Ventas usuarioActualizado = ventasService.actualizar(codigo, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Ventas>> listarActivos(){
        List<Ventas> UsuariosA = ventasService.listarActivos();
        return ResponseEntity.ok(UsuariosA);
    }

}
