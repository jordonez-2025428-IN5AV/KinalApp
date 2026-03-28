package com.jeremyjuarez.kinalapp.controller;

import com.jeremyjuarez.kinalapp.entity.Usuario;
import com.jeremyjuarez.kinalapp.service.IUsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {
    private final IUsuarioService userService;

    public UsuarioController(IUsuarioService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar(){
        List<Usuario> usuario = userService.listarUsuarios();
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Usuario> buscarPorDPI(@PathVariable String codigo){
        return userService.buscarPorCodigo(codigo).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario ){
        try {
            Usuario nuevoUsuario = userService.guardar(usuario);
            return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> eliminar(@PathVariable String codigo){
        try {
            if (!userService.existePorCodigo(codigo)){
                return ResponseEntity.notFound().build();
            }
            userService.eliminar(codigo);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<?> actualizar(@PathVariable String codigo, @RequestBody Usuario usuario){
        try {
            if (!userService.existePorCodigo(codigo)){
                return ResponseEntity.notFound().build();
            }
            Usuario usuarioActualizado = userService.actualizar(codigo, usuario);
            return ResponseEntity.ok(usuarioActualizado);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return  ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> listarActivos(){
        List<Usuario> UsuariosA = userService.listarActivos();
        return ResponseEntity.ok(UsuariosA);
    }


}
