package com.jeremyjuarez.kinalapp.controller;

import com.jeremyjuarez.kinalapp.entity.*;
import com.jeremyjuarez.kinalapp.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view")
public class ViewController {

    private final IUsuarioService usuarioService;
    private final IProductosService productoService;
    private final IClienteService clienteService;
    private final IVentasService ventaService;
    private final IDetalleVentaService detalleService;

    public ViewController(IUsuarioService usuarioService, IProductosService productoService,
                          IClienteService clienteService, IVentasService ventaService,
                          IDetalleVentaService detalleService) {
        this.usuarioService = usuarioService;
        this.productoService = productoService;
        this.clienteService = clienteService;
        this.ventaService = ventaService;
        this.detalleService = detalleService;
    }

    @GetMapping("/usuarios")
    public String viewUsuarios(Model model) {
        model.addAttribute("lista", usuarioService.listarUsuarios());
        return "usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "form-usuario";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String editarUsuario(@PathVariable String id, Model model) {
        Usuario usuario = usuarioService.buscarPorCodigo(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        return "form-usuario";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/view/usuarios";
    }

    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable String id) {
        usuarioService.eliminar(id);
        return "redirect:/view/usuarios";
    }

    @GetMapping("/usuarios/buscar")
    public String buscarUsuario(@RequestParam("q") String query, Model model) {
        var lista = usuarioService.listarUsuarios().stream()
                .filter(u -> u.getCodigoUsuario().contains(query) ||
                        u.getUsername().toLowerCase().contains(query.toLowerCase()))
                .toList();
        model.addAttribute("lista", lista);
        return "usuarios";
    }

    @GetMapping("/productos")
    public String viewProductos(Model model) {
        model.addAttribute("lista", productoService.listarUsuarios());
        return "productos";
    }

    @GetMapping("/productos/nuevo")
    public String nuevoProducto(Model model) {
        model.addAttribute("producto", new Productos());
        return "form-producto";
    }

    @GetMapping("/productos/editar/{id}")
    public String editarProducto(@PathVariable String id, Model model) {
        Productos producto = productoService.buscarPorCodigoP(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        model.addAttribute("producto", producto);
        return "form-producto";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Productos producto) {
        productoService.guardar(producto);
        return "redirect:/view/productos";
    }

    @GetMapping("/productos/eliminar/{id}")
    public String eliminarProducto(@PathVariable String id) {
        productoService.eliminar(id);
        return "redirect:/view/productos";
    }

    @GetMapping("/productos/buscar")
    public String buscarProducto(@RequestParam("q") String query, Model model) {
        var lista = productoService.listarUsuarios().stream()
                .filter(p -> p.getCodigoProducto().contains(query) ||
                        p.getNombreProducto().toLowerCase().contains(query.toLowerCase()))
                .toList();
        model.addAttribute("lista", lista);
        return "productos";
    }

    @GetMapping("/clientes")
    public String viewClientes(Model model) {
        model.addAttribute("lista", clienteService.listarClientes());
        return "clientes";
    }

    @GetMapping("/clientes/nuevo")
    public String nuevoCliente(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "form-cliente";
    }

    @GetMapping("/clientes/editar/{id}")
    public String editarCliente(@PathVariable("id") String dpi, Model model) {
        Cliente cliente = clienteService.buscarPorDPI(dpi)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        model.addAttribute("cliente", cliente);
        return "form-cliente";
    }

    @PostMapping("/clientes/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/view/clientes";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") String dpi) {
        clienteService.eliminar(dpi);
        return "redirect:/view/clientes";
    }

    @GetMapping("/clientes/buscar")
    public String buscarCliente(@RequestParam(value = "dpi", required = false) String dpi,
                                Model model) {
        if (dpi == null || dpi.trim().isEmpty()) {
            model.addAttribute("lista", clienteService.listarClientes());
            return "clientes";
        }

        var clienteOpt = clienteService.buscarPorDPI(dpi);

        if (clienteOpt.isPresent()) {
            model.addAttribute("lista", java.util.List.of(clienteOpt.get()));
        } else {
            model.addAttribute("lista", java.util.List.of());
        }

        return "clientes";
    }

    @GetMapping("/ventas")
    public String viewVentas(Model model) {
        model.addAttribute("lista", ventaService.listarVentas());
        return "ventas";
    }

    @GetMapping("/ventas/nuevo")
    public String nuevaVenta(Model model) {
        model.addAttribute("venta", new Ventas());
        return "form-venta";
    }

    @GetMapping("/ventas/editar/{id}")
    public String editarVenta(@PathVariable String id, Model model) {
        Ventas venta = ventaService.buscarPorCodigo(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        model.addAttribute("venta", venta);
        return "form-venta";
    }

    @PostMapping("/ventas/guardar")
    public String guardarVenta(@ModelAttribute Ventas venta) {
        ventaService.guardar(venta);
        return "redirect:/view/ventas";
    }

    @GetMapping("/ventas/eliminar/{id}")
    public String eliminarVenta(@PathVariable String id) {
        ventaService.eliminar(id);
        return "redirect:/view/ventas";
    }

    @GetMapping("/ventas/buscar")
    public String buscarVenta(@RequestParam("q") String query, Model model) {
        var lista = ventaService.listarVentas().stream()
                .filter(v -> v.getCodigoVenta().contains(query))
                .toList();
        model.addAttribute("lista", lista);
        return "ventas";
    }

    @GetMapping("/detalle-ventas")
    public String viewDetalles(Model model) {
        model.addAttribute("lista", detalleService.listarDetalles());
        return "detalle-ventas";
    }

    @GetMapping("/detalle-ventas/nuevo")
    public String nuevoDetalle(Model model) {
        model.addAttribute("detalle", new DetalleVenta());
        return "form-detalle";
    }

    @GetMapping("/detalle-ventas/editar/{id}")
    public String editarDetalle(@PathVariable String id, Model model) {
        DetalleVenta detalle = detalleService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Detalle no encontrado"));
        model.addAttribute("detalle", detalle);
        return "form-detalle";
    }

    @PostMapping("/detalle-ventas/guardar")
    public String guardarDetalle(@ModelAttribute DetalleVenta detalle) {
        detalleService.guardar(detalle);
        return "redirect:/view/detalle-ventas";
    }

    @GetMapping("/detalle-ventas/eliminar/{id}")
    public String eliminarDetalle(@PathVariable String id) {
        detalleService.eliminar(id);
        return "redirect:/view/detalle-ventas";
    }

    @GetMapping("/detalle-ventas/buscar")
    public String buscarDetalle(@RequestParam(value = "codigo", required = false) String codigo,
                                Model model) {
        if (codigo == null || codigo.trim().isEmpty()) {
            model.addAttribute("lista", detalleService.listarDetalles());
            return "detalle-ventas";
        }

        var detalleOpt = detalleService.buscarPorId(codigo);

        if (detalleOpt.isPresent()) {
            model.addAttribute("lista", java.util.List.of(detalleOpt.get()));
        } else {
            model.addAttribute("lista", java.util.List.of());
        }

        return "detalle-ventas";
    }
}