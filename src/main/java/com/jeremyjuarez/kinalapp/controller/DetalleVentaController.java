package com.jeremyjuarez.kinalapp.controller;

import com.jeremyjuarez.kinalapp.entity.DetalleVenta;
import com.jeremyjuarez.kinalapp.service.IDetalleVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles")
public class DetalleVentaController {

    private final IDetalleVentaService detalleVentaService;

    public DetalleVentaController(IDetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar() {
        return ResponseEntity.ok(detalleVentaService.listarDetalles());
    }

    @GetMapping("/buscar/{codigo}")
    public ResponseEntity<DetalleVenta> buscarPorId(@PathVariable String codigo) {
        return detalleVentaService.buscarPorId(codigo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody DetalleVenta detalleVenta) {
        try {
            return new ResponseEntity<>(
                    detalleVentaService.guardar(detalleVenta),
                    HttpStatus.CREATED
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo) {
        if (detalleVentaService.buscarPorId(codigo).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        detalleVentaService.eliminar(String.valueOf(codigo));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable String codigo,
                                        @RequestBody DetalleVenta detalleVenta) {

        if (detalleVentaService.buscarPorId(codigo).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        detalleVenta.setCodigoDetalleVenta(String.valueOf(codigo));
        return ResponseEntity.ok(detalleVentaService.guardar(detalleVenta));
    }

    @GetMapping("/activos")
    public ResponseEntity<List<DetalleVenta>> listarActivos() {
        return ResponseEntity.ok(detalleVentaService.listarDetalles());
    }
}
