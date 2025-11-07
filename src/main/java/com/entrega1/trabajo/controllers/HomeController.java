package com.entrega1.trabajo.controllers;

import java.security.Principal;

import com.entrega1.trabajo.DTOs.ApiDTO;
import com.entrega1.trabajo.service.ApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entrega1.trabajo.service.UserDetailsServiceImpl;
import com.entrega1.trabajo.model.Referee;

@Controller
public class HomeController {
    @Autowired
    private com.entrega1.trabajo.repository.RefereeRequestRepository refereeRequestRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    @Autowired
    private ApiService apiService;

    @GetMapping("/")
    public String index(Model model, Principal principal){
        Referee referee = userDetailsService.getRefereeByUsername(principal.getName());

        model.addAttribute("title", "Entrega 1 -- Estructura de Software");

        if (referee == null && !principal.getName().equals("admin")) {
            return "home/no_count";
        } else {
            model.addAttribute("user", referee);
            if (referee != null) {
                model.addAttribute("refereeRequests", refereeRequestRepository.findByRefereeId(referee.getId()));
            }
            return "home/index";
        }
        
        
    }

    @GetMapping("/table")
    public String mostrarClima(@RequestParam double lat, @RequestParam double lon, Model model) {
        ApiDTO clima = apiService.obtenerClima(lat, lon);
        model.addAttribute("clima", clima);
        return "home/table";
    }

}
