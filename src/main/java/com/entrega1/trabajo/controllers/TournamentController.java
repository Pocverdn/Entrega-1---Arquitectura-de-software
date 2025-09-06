package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Tournament;
import com.entrega1.trabajo.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    private final TournamentService tournamentService;

    public TournamentController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("tournaments", tournamentService.findAll());
        return "tournaments/index";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("tournament", new Tournament());
        return "tournaments/form";
    }

    @PostMapping
    public String save(@ModelAttribute Tournament tournament) {
        tournamentService.save(tournament);
        return "tournaments/success";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Tournament tournament = tournamentService.findById(id).orElseThrow();
        model.addAttribute("tournament", tournament);
        return "tournaments/show";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id, Model model) {
        tournamentService.deleteById(id);
        model.addAttribute("tournaments", tournamentService.findAll());
        return "tournaments/index";
    }
}
