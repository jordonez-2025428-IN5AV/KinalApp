package com.jeremyjuarez.kinalapp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @PostMapping("/auth/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {

        // Validación básica (aquí podrías consultar a tu DB)
        if ("admin".equals(username) && "123".equals(password)) {
            // Guardamos el usuario en la sesión para saber que está logueado
            session.setAttribute("usuarioLogueado", username);

            // Redirigimos al index
            return "redirect:/home";
        } else {
            // Si falla, enviamos un mensaje de error y volvemos al login
            model.addAttribute("error", "Credenciales incorrectas");
            return "login";
        }
    }

}
