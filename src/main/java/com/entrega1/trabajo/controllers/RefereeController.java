package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Referee;
import com.entrega1.trabajo.service.RefereeService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/referees")
public class RefereeController {

    private final RefereeService refereeService;
    
    public RefereeController(RefereeService refereeService) {
        this.refereeService = refereeService;
    }

    @GetMapping("/form")
    public String refereeForm(Model model){
        model.addAttribute("referee", new Referee());
        return "referees/form";
    }

    @PostMapping("/create")
    public String createReferee(@ModelAttribute Referee referee, @RequestParam("file") MultipartFile file, Model model) {
        
        try {
            if (!file.isEmpty()) {
                referee.setPhoto(file.getBytes());
            }
            refereeService.createReferee(referee);
            model.addAttribute("message", "Referee has been created successfully!");
            return "referees/success";
        } catch (Exception e) {

        }
        return "referees/form";
    }
        

    @GetMapping("/index")
    public String refereeIndex(Model model){
        model.addAttribute("referees", refereeService.getAllReferees());
        return "referees/index";
    }

    @GetMapping("/{id}")
    public String refereeShow(@PathVariable int id, Model model){
        Referee referee = refereeService.getRefereeById(id).orElseThrow(() -> new IllegalArgumentException("Invalid referee Id: " + id));
        model.addAttribute("referee", referee);
        return "referees/show";
    }

    @PostMapping("/{id}/delete")
    public String deleteReferee(@PathVariable int id, Model model) {
        refereeService.deleteRefereeById(id);
        model.addAttribute("referees", refereeService.getAllReferees());
        return "referees/index";
    }

    
}