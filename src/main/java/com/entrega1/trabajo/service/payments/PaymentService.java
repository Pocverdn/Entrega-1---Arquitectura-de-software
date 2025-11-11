package com.entrega1.trabajo.service.payments;

import com.entrega1.trabajo.model.Referee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {

    private final PaymentMethod paymentMethod;

   
    public PaymentService(@Qualifier("chequePayment") PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String pagarReferee(Referee referee, double monto) {
        return paymentMethod.procesarPago(referee, monto);
    }
}
