package com.entrega1.trabajo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.entrega1.trabajo.service.UserDetailsServiceImpl;
import com.entrega1.trabajo.model.Referee;
import com.entrega1.trabajo.service.RefereeService;


@Controller
public class HomeController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @GetMapping("/")
    public String index(Model model, Principal principal){
        Referee referee = userDetailsService.getRefereeByUsername(principal.getName());

        System.out.println(referee);

        model.addAttribute("title", "Entrega 1 -- Estructura de Software");

        if (referee == null && !principal.getName().equals("admin")) {
            return "home/no_count";
        }else {
            model.addAttribute("user", referee);
            return "home/index";
        }
        
        
    }

}
