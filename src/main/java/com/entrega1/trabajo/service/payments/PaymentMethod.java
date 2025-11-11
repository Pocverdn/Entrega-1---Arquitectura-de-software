package com.entrega1.trabajo.service.payments;

import com.entrega1.trabajo.model.Referee;

/**
 * Interfaz que define el comportamiento genérico para realizar pagos a árbitros.
 */
public interface PaymentMethod {

    /**
     * Procesa el pago de un árbitro por el monto indicado.
     * @param arbitro árbitro que recibirá el pago
     * @param monto valor del pago
     * @return mensaje con el resultado del pago
     */
    String procesarPago(Referee referee, double monto);
}
