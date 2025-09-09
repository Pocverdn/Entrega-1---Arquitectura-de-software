package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.RefereeRequest;
import com.entrega1.trabajo.repository.RefereeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/requests")
public class RefereeRequestController {

    @Autowired
    private RefereeRequestRepository requestRepository;

    @PostMapping("/{id}/accept")
    public String accept(@PathVariable Integer id) {
        RefereeRequest req = requestRepository.findById(id).orElseThrow();
        req.setStatus("aceptada");
        requestRepository.save(req);
        return "redirect:/profile"; // Ajusta la ruta según tu vista de perfil
    }

    @PostMapping("/{id}/reject")
    public String reject(@PathVariable Integer id) {
        RefereeRequest req = requestRepository.findById(id).orElseThrow();
        req.setStatus("rechazada");
        requestRepository.save(req);
        return "redirect:/profile";
    }
}
