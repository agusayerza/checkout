package com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago;

import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto.MPPaymentDto;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.springframework.stereotype.Component;

@Component("Fake")
public class FakePaymentService implements PaymentService {
    @Override
    public Payment.Status createPayment(MPPaymentDto mpPaymentDto) throws MPException {
        if(mpPaymentDto.getPayer().getEmail().toLowerCase().contains("apro")){
            return Payment.Status.approved;
        } else {
            return Payment.Status.rejected;
        }
    }
}
