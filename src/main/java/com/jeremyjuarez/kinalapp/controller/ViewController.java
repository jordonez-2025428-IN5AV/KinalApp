package com.jeremyjuarez.kinalapp.controller;

import com.jeremyjuarez.kinalapp.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view")
public class ViewController {

    // Inyectamos todos los servicios necesarios
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

    // Middleware manual para validar sesión
    private boolean isNotLogged(HttpSession session) {
        return session.getAttribute("usuarioLogueado") == null;
    }

    @GetMapping("/usuarios")
    public String viewUsuarios(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("lista", usuarioService.listarUsuarios());
        return "usuarios"; // Busca templates/usuarios.html
    }

    @GetMapping("/productos")
    public String viewProductos(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("lista", productoService.listarUsuarios());
        return "productos";
    }

    @GetMapping("/clientes")
    public String viewClientes(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("lista", clienteService.listarClientes());
        return "clientes";
    }

    @GetMapping("/ventas")
    public String viewVentas(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("lista", ventaService.listarUsuarios());
        return "ventas";
    }

    @GetMapping("/detalle-ventas")
    public String viewDetalles(Model model, HttpSession session) {
        if (isNotLogged(session)) return "redirect:/";
        model.addAttribute("lista", detalleService.listarUsuarios());
        return "detalle-ventas";
    }
}