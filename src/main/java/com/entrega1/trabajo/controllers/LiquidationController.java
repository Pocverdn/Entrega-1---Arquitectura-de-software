package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Liquidation;
import com.entrega1.trabajo.service.LiquidationService;
import com.entrega1.trabajo.service.RefereeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/liquidations")
public class LiquidationController {

    private final LiquidationService liquidationService;
    private final RefereeService refereeService;

    public LiquidationController(LiquidationService liquidationService, RefereeService refereeService) {
        this.liquidationService = liquidationService;
        this.refereeService = refereeService;
    }

    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("liquidations", liquidationService.findAll());
        return "liquidations/index";
    }

    
    @GetMapping("/index")
    public String indexAlias(Model model) {
        model.addAttribute("liquidations", liquidationService.findAll());
        return "liquidations/index";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("liquidation", new Liquidation());
        model.addAttribute("referees", refereeService.findAll());
        return "liquidations/form";
    }

    @PostMapping
    public String save(@ModelAttribute Liquidation liquidation) {
        liquidationService.save(liquidation);
        return "liquidations/success";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Liquidation liquidation = liquidationService.findById(id).orElseThrow();
        model.addAttribute("liquidation", liquidation);
        return "liquidations/show";
    }

    
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Integer id) {
        liquidationService.deleteById(id);
        return "redirect:/liquidations";
    }
}
