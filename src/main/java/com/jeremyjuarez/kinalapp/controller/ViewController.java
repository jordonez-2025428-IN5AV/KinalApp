package com.jeremyjuarez.kinalapp.controller;

import com.jeremyjuarez.kinalapp.entity.*;
import com.jeremyjuarez.kinalapp.service.*;
import jakarta.servlet.http.HttpSession;
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

    private boolean isNotLogged(HttpSession session) {
        return session.getAttribute("usuarioLogueado") == null;
    }

    // ================= USUARIOS =================
    @GetMapping("/usuarios")
    public String viewUsuarios(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("lista", usuarioService.listarUsuarios());
        return "usuarios";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("usuario", new Usuario());
        return "form-usuario";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.guardar(usuario);
        return "redirect:/view/usuarios";
    }

    // ================= PRODUCTOS =================
    @GetMapping("/productos")
    public String viewProductos(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("lista", productoService.listarUsuarios());
        return "productos";
    }

    @GetMapping("/productos/nuevo")
    public String nuevoProducto(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("producto", new Productos());
        return "form-producto";
    }

    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute Productos producto) {
        productoService.guardar(producto);
        return "redirect:/view/productos";
    }

    // ================= CLIENTES =================
    @GetMapping("/clientes")
    public String viewClientes(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("lista", clienteService.listarClientes());
        return "clientes";
    }

    @GetMapping("/clientes/nuevo")
    public String nuevoCliente(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("cliente", new Cliente());
        return "form-cliente";
    }

    // EDITAR (cargar datos en el formulario)
    @GetMapping("/clientes/editar/{id}")
    public String editarCliente(@PathVariable("id") String dpi, Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";

        Cliente cliente = clienteService.buscarPorDPI(dpi)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        model.addAttribute("cliente", cliente);
        return "form-cliente";
    }

    // GUARDAR (crear + actualizar)
    @PostMapping("/clientes/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/view/clientes";
    }

    // ELIMINAR
    @GetMapping("/clientes/eliminar/{id}")
    public String eliminarCliente(@PathVariable("id") String dpi, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";

        clienteService.eliminar(dpi);
        return "redirect:/view/clientes";
    }

    @GetMapping("/clientes/buscar")
    public String buscarCliente(@RequestParam(value = "dpi", required = false) String dpi,
                                Model model, HttpSession session) {

        if (isNotLogged(session)) return "redirect:/";

        if (dpi == null || dpi.trim().isEmpty()) {
            model.addAttribute("lista", clienteService.listarClientes());
            return "clientes";
        }

        var clienteOpt = clienteService.buscarPorDPI(dpi);

        if (clienteOpt.isPresent()) {
            model.addAttribute("lista", java.util.List.of(clienteOpt.get()));
        } else {
            model.addAttribute("lista", java.util.List.of()); // lista vacía
        }

        return "clientes";
    }

    // ================= VENTAS =================
    @GetMapping("/ventas")
    public String viewVentas(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";

        model.addAttribute("lista", ventaService.listarVentas());
        return "ventas";
    }

    @GetMapping("/ventas/nuevo")
    public String nuevaVenta(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("venta", new Ventas());
        return "form-venta";
    }

    @PostMapping("/ventas/guardar")
    public String guardarVenta(@ModelAttribute Ventas venta) {
        ventaService.guardar(venta);
        return "redirect:/view/ventas";
    }

    // ================= DETALLE =================
    @GetMapping("/detalle-ventas")
    public String viewDetalles(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";

        model.addAttribute("lista", detalleService.listarDetalles());
        return "detalle-ventas";
    }

    @GetMapping("/detalle-ventas/nuevo")
    public String nuevoDetalle(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("detalle", new DetalleVenta());
        return "form-detalle";
    }

    @PostMapping("/detalle-ventas/guardar")
    public String guardarDetalle(@ModelAttribute DetalleVenta detalle) {
        detalleService.guardar(detalle);
        return "redirect:/view/detalle-ventas";
    }
}