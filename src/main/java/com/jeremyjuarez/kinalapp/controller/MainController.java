package com.jeremyjuarez.kinalapp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    public String loginView() {
        return "login";
    }

    @PostMapping("/login")
    public String autenticar(@RequestParam String user, @RequestParam String password,
                             HttpSession session, Model model) {
        // Validación manual
        if ("admin".equals(user) && "123".equals(password)) {
            session.setAttribute("usuarioLogueado", user);
            return "redirect:/index";
        }
        model.addAttribute("error", "Credenciales incorrectas");
        return "login";
    }

    @GetMapping("/index")
    public String index(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/";
        return "index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}