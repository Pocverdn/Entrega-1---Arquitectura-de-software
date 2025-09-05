package com.entrega1.trabajo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Esto asume que tienes un archivo login.html en src/main/resources/templates
    }
}