package com.entrega1.trabajo.controllers;

import com.entrega1.trabajo.model.Referee;
import com.entrega1.trabajo.service.payments.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagos")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

   
    @PostMapping("/simular")
    public String simularPago(@RequestParam String nombre,
                              @RequestParam double saldo,
                              @RequestParam double monto) {

        Referee referee = new Referee();
        referee.setName(nombre);
        referee.setSaldoDisponible(saldo);

        return paymentService.pagarReferee(referee, monto);
    }
}
