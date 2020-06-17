package com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceProvider {

    @Value("${payment.service.implementation:Production}")
    private String current;

    private final PaymentService production;


    public PaymentServiceProvider(@Qualifier("Production") PaymentService production) {
        this.production = production;
    }

    public PaymentService getInstance(){
        switch(current) {
            case "Production": return production;
            default:
                throw new IllegalArgumentException();
        }
    }
}
