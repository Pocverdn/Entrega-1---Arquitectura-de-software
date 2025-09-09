package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Game;
import com.entrega1.trabajo.service.GameService;
import com.entrega1.trabajo.repository.RefereeRepository;
import com.entrega1.trabajo.repository.TournamentRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/games")
public class GameController {
    @org.springframework.beans.factory.annotation.Autowired
    private com.entrega1.trabajo.repository.RefereeRequestRepository refereeRequestRepository;
    private final GameService juegoService;
    private final RefereeRepository refereeRepository;
    private final TournamentRepository tournamentRepository;

    public GameController (GameService juegoService, RefereeRepository refereeRepository, TournamentRepository tournamentRepository) {
        this.juegoService = juegoService;
        this.refereeRepository = refereeRepository;
        this.tournamentRepository = tournamentRepository;
    }
    
   @GetMapping("/index")
    public String index(Model model) {
       model.addAttribute("games", juegoService.dateSort());
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
        model.addAttribute("referees", refereeRepository.findAll()); 
        model.addAttribute("tournaments", tournamentRepository.findAll()); 
        return "games/form";
    }

    @PostMapping
    public String save(@ModelAttribute Game juego) {

        juegoService.save(juego);
        // Crear solicitud de arbitraje si hay árbitro asignado
        if (juego.getReferee() != null) {
            com.entrega1.trabajo.model.RefereeRequest req = new com.entrega1.trabajo.model.RefereeRequest();
            req.setReferee(juego.getReferee());
            req.setGame(juego);
            req.setStatus("pendiente");
            refereeRequestRepository.save(req);
        }
        return "games/success";

    }
    

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        juegoService.deleteById(id);
        model.addAttribute("games", juegoService.findAll());
        return "tournaments/index";
    }


}
