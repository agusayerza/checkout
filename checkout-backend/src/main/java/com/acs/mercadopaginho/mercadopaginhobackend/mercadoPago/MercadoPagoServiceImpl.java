package com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago;

import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto.MPPaymentDto;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Payer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component("Production")
public class MercadoPagoServiceImpl implements PaymentService {

    public Payment.Status createPayment(MPPaymentDto mpPaymentDto) {
        MercadoPago.SDK.configure("TEST-450628718899153-120822-ee5c6f443c3f8075d093e5b036dc61bf-389322459");

        Payment payment = new Payment();
        payment.setTransactionAmount(mpPaymentDto.getTransaction_amount())
                .setToken(mpPaymentDto.getToken())
                .setDescription("MercadoPaginho")
                .setInstallments(mpPaymentDto.getInstallments())
                .setPaymentMethodId(mpPaymentDto.getPayment_method_id())
                .setIssuerId(mpPaymentDto.getIssuerId())
                .setPayer(new Payer()
                        .setEmail(mpPaymentDto.getPayer().getEmail()));
        try {
            Payment p = payment.save();
        } catch (MPException e) {
            e.printStackTrace();
        }
        return payment.getStatus();
}
}
