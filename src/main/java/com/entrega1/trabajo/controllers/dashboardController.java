package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.service.TournamentService;

import com.entrega1.trabajo.service.GameService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.session.SessionRegistry;


@Controller
@RequestMapping("/dashboard")
public class dashboardController {
    @org.springframework.beans.factory.annotation.Autowired
    private com.entrega1.trabajo.repository.RefereeRequestRepository refereeRequestRepository;

    private final TournamentService tournamentService;
    private final GameService gameService;
    private final SessionRegistry sessionRegistry;

    public dashboardController(TournamentService tournamentService, GameService gameService , SessionRegistry sessionRegistry) {
        this.tournamentService = tournamentService;
        this.gameService = gameService;
        this.sessionRegistry = sessionRegistry;
    }

    @GetMapping("/index")
    public String getActiveUsers(Model model) {
        int activeUsers = (int) sessionRegistry.getAllPrincipals().stream()
            .filter(principal -> !sessionRegistry.getAllSessions(principal, false).isEmpty())
            .count();

        
        var onlineUsers = sessionRegistry.getAllPrincipals().stream()
            .filter(principal -> !sessionRegistry.getAllSessions(principal, false).isEmpty())
            .map(principal -> {
                if (principal instanceof org.springframework.security.core.userdetails.UserDetails userDetails) {
                    return userDetails.getUsername();
                } else {
                    return principal.toString();
                }
            })
            .toList();

    model.addAttribute("activeUsers", activeUsers);
    model.addAttribute("onlineUsers", onlineUsers);
    model.addAttribute("tournaments", tournamentService.activeTournaments());
    model.addAttribute("games", gameService.gameFuture());
    model.addAttribute("allRequests", refereeRequestRepository.findAll());

    return "dashboard/index";
    }
}
