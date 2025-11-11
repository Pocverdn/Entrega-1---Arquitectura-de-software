package com.entrega1.trabajo.service.payments;

import com.entrega1.trabajo.model.Referee;


public interface PaymentMethod {

    /**
     * Procesa el pago de un referee por el monto indicado.
     *
     * @param referee 
     * @param monto   
     * @return 
     */
    String procesarPago(Referee referee, double monto);
}
