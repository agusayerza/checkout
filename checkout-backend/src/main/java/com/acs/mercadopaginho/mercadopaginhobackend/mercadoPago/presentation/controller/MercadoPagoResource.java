package com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.presentation.controller;

import com.acs.mercadopaginho.mercadopaginhobackend.DemoRunner;
import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto.MPPaymentDto;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Payer;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mercado-pago")
public class MercadoPagoResource {


    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DemoRunner.class);

    @PostMapping("/payment")
    public ResponseEntity createPayment(@RequestBody MPPaymentDto mpPaymentDto){
        Payment payment = new Payment();
        payment.setTransactionAmount(mpPaymentDto.getTransaction_amount())
                .setToken(mpPaymentDto.getToken())
                .setDescription("Gorgeous Leather Shirt")
                .setInstallments(mpPaymentDto.getInstallments())
                .setPaymentMethodId(mpPaymentDto.getPayment_method_id())
                .setPayer(new Payer()
                        .setEmail(mpPaymentDto.getPayer().getEmail()));
        try {
            payment.save();
            if(payment.getStatus() == Payment.Status.approved) {
//                TODO crear sale con el checkout id
                return new ResponseEntity<>(payment.getStatus(), HttpStatus.OK);
            }else{
                return new ResponseEntity<>(payment.getStatus(), HttpStatus.BAD_REQUEST);
            }
        } catch (MPException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
