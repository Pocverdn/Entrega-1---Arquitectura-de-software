package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Referee;
import com.entrega1.trabajo.service.payments.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pagos")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Muestra el formulario
    @GetMapping("/simular")
    public String mostrarFormularioPago(Model model) {
        model.addAttribute("resultado", null);
        return "payments/simular-pago";
    }

    // Procesa el formulario
    @PostMapping("/simular")
    public String procesarPago(@RequestParam String nombre,
                               @RequestParam double saldo,
                               @RequestParam double monto,
                               Model model) {

        Referee referee = new Referee();
        referee.setName(nombre);
        referee.setSaldoDisponible(saldo);

        String resultado = paymentService.pagarReferee(referee, monto);

        model.addAttribute("resultado", resultado);
        return "payments/simular-pago";
    }
}
