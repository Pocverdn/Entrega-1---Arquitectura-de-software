package com.entrega1.trabajo.service.payments;

import com.entrega1.trabajo.model.Referee;
import org.springframework.stereotype.Service;


@Service
public class ChequePayment implements PaymentMethod {

    @Override
    public String procesarPago(Referee referee, double monto) {
        String pdfSimulado = """
                ======================================
                          CHEQUE DE PAGO
                ======================================
                Referee: %s
                Monto: $%.2f
                Banco: Banco Simulado EAFIT
                --------------------------------------
                Firma Autorizada: ___________________
                ======================================
                """.formatted(referee.getName(), monto);

      
        System.out.println(pdfSimulado);

        return "Cheque generado para el referee " + referee.getName() + " por $" + monto;
    }
}
