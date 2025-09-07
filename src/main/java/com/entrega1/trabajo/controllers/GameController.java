package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Game;
import com.entrega1.trabajo.service.GameService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/games")
public class GameController {
    private final GameService juegoService;

    public GameController (GameService juegoService) {
        this.juegoService = juegoService;

    }
    
   @GetMapping("/index")
    public String index(Model model) {
       model.addAttribute("games", juegoService.findAll());
       return "games/index";
    }   

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        Game game = juegoService.findById(id).orElseThrow();
        model.addAttribute("game", game);
        return "games/show";
    }  

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("game", new Game());
        return "games/form";
    }

    @PostMapping
    public String save(@ModelAttribute Game juego) {

        juegoService.save(juego);
        return "games/success";

    }
    

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        juegoService.deleteById(id);
        model.addAttribute("games", juegoService.findAll());
        return "tournaments/index";
    }


}
