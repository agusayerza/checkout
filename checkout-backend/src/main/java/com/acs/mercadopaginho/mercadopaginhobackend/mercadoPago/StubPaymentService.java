package com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago;

import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto.MPPaymentDto;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.springframework.stereotype.Component;

@Component("Stub")
public class StubPaymentService implements PaymentService {
    @Override
    public Payment.Status createPayment(MPPaymentDto mpPaymentDto) throws MPException {
        return Payment.Status.approved;
    }
}
