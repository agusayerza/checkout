package com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago;

import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto.MPPaymentDto;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {

    Payment.Status createPayment(MPPaymentDto mpPaymentDto) throws MPException;
}
