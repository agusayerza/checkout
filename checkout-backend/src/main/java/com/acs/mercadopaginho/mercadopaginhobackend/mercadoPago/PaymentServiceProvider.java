package com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceProvider {

    @Value("${payment.service.implementation:Production}")
    private String current;

    private final PaymentService production;

    private final PaymentService fake;

    private final PaymentService stub;

    public PaymentServiceProvider(@Qualifier("Production") PaymentService production, @Qualifier("Fake") PaymentService fake, @Qualifier("Stub") PaymentService stub) {
        this.production = production;
        this.fake = fake;
        this.stub = stub;
    }

    public PaymentService getInstance(){
        switch(current) {
            case "Production": return production;
            case "Fake": return fake;
            case "Stub": return stub;
            default:
                throw new IllegalArgumentException();
        }
    }
}
