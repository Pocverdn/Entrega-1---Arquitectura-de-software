package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Juego;
import com.entrega1.trabajo.model.Liquidation;
import com.entrega1.trabajo.model.Referee;
import com.entrega1.trabajo.model.Tournament;
import com.entrega1.trabajo.repository.JuegoRepository;
import com.entrega1.trabajo.service.JuegoService;
import com.entrega1.trabajo.service.RefereeService;
import com.entrega1.trabajo.service.TournamentService;

import java.util.Date;
import java.util.HashMap;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class JuegoController {
     private final JuegoService juegoService;
    public JuegoController (JuegoService juegoService) {
            this.juegoService = juegoService;

    }
    


   @GetMapping("/juegos")
   public String index(Model model) {
       model.addAttribute("juego", juegoService.findAll());
       return "juego/index";
   }   

    @GetMapping("/juegos/{id}")
   public String show(@PathVariable int id, Model model) {
        Juego juego = juegoService.findById(id).orElseThrow();
        model.addAttribute("juego", juego);
       return "juego/show";
   }  

    @GetMapping("/juegos/create")
   public String create(Model model) {
        model.addAttribute("juego", new Juego());
        //model.addAttribute("referees", refereeRepository.findAll()); 
       return "juego/create";
   }

    @PostMapping("/juego/save")
   public String save(@ModelAttribute Juego juego) {

           juegoService.save(juego);
           return "juego/create";

   }
     
    @GetMapping("/juegos/succes")
   public String succes(){

       return "juego/succes";
   }

    @GetMapping("/juegos/{id}/delete")
    public String delete(@PathVariable int id) {
        juegoService.deleteById(id);
        return "redirect:/juegos";
    }


}
