package com.entrega1.trabajo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("title", "Entrega 1 -- Estructura de Software");
        return "home/index";
    }

}
