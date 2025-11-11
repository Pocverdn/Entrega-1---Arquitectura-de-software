package com.entrega1.trabajo.service.payments;

import com.entrega1.trabajo.model.Referee;
import org.springframework.stereotype.Service;

/**
 * Implementación que simula pagos restando el saldo disponible del referee.
 */
@Service
public class BalancePayment implements PaymentMethod {

    @Override
    public String procesarPago(Referee referee, double monto) {
        if (referee.getSaldoDisponible() >= monto) {
            double nuevoSaldo = referee.getSaldoDisponible() - monto;
            referee.setSaldoDisponible(nuevoSaldo);
            return "Pago realizado con éxito. Nuevo saldo de " + referee.getName() + ": $" + nuevoSaldo;
        } else {
            return "Saldo insuficiente para realizar el pago de " + referee.getName();
        }
    }
}
