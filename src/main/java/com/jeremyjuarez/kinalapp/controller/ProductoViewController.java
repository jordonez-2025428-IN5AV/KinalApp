package com.jeremyjuarez.kinalapp.controller;

import com.jeremyjuarez.kinalapp.service.IProductosService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/productos") // Usamos una ruta distinta para la vista
public class ProductoViewController {

    private final IProductosService productosService;

    public ProductoViewController(IProductosService productosService) {
        this.productosService = productosService;
    }

    @GetMapping
    public String listarProductos(Model model) {
        // Obtenemos la lista desde el service
        model.addAttribute("listaProductos", productosService.listarUsuarios());
        // Retornamos el nombre del archivo HTML (productos.html)
        return "productos";
    }
}