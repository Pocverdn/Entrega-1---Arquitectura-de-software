package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Referee;
import com.entrega1.trabajo.service.RefereeService;

import com.entrega1.trabajo.model.Tournament;
import com.entrega1.trabajo.service.TournamentService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
@RequestMapping("/dashboard")
public class dashboardController {

    private final TournamentService tournamentService;
    private final RefereeService refereeService;
    private final SessionRegistry sessionRegistry;

    public dashboardController(TournamentService tournamentService, RefereeService refereeService , SessionRegistry sessionRegistry) {
        this.tournamentService = tournamentService;
        this.refereeService = refereeService;
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
        model.addAttribute("tournaments", tournamentService.findAll());

        return "dashboard/index";
    }
}
