package com.acs.mercadopaginho.mercadopaginhobackend.sale.business.service.impl;

import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model.CheckOut;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.repository.CheckOutRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.PaymentService;
import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.PaymentServiceProvider;
import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto.MPPaymentDto;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.business.service.SaleService;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.factory.SaleFactory;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.persistence.model.Sale;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.persistence.repository.SaleRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.presentation.dto.CreateSaleDto;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.repository.UserRepository;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleRepository saleRepository;
    private PaymentService paymentService;
    private PaymentServiceProvider paymentServiceProvider;
    private UserRepository userRepository;
    private CheckOutRepository checkOutRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository,
                           UserRepository userRepository, CheckOutRepository checkOutRepository, PaymentServiceProvider paymentServiceProvider) {
        this.saleRepository = saleRepository;
        this.paymentService = paymentServiceProvider.getInstance();
        this.paymentServiceProvider = paymentServiceProvider;
        this.userRepository = userRepository;
        this.checkOutRepository = checkOutRepository;
    }

    @Override
    public Sale validateAndCreate(MPPaymentDto mpPaymentDto) throws MPException {
        Payment.Status status = paymentService.createPayment(mpPaymentDto);
        if (!status.equals(Payment.Status.approved))
            throw new IllegalArgumentException("Payment not approved. Status was " + status);
        CreateSaleDto createSaleDto = new CreateSaleDto(mpPaymentDto.getUserId(), mpPaymentDto.getCheckoutId());
        return create(createSaleDto);
    }

    @Override
    public Sale create(CreateSaleDto createSaleDto) {
        Optional<User> optionalUser = userRepository.findById(createSaleDto.getUserId());
        if (!optionalUser.isPresent()) throw new IllegalArgumentException("User not found");
        Optional<CheckOut> optionalCheckOut = checkOutRepository.findById(createSaleDto.getCheckOutId());
        if (!optionalCheckOut.isPresent()) throw new IllegalArgumentException("CheckOut not found");
        Sale sale = SaleFactory.create(optionalUser.get(), optionalCheckOut.get());
        saleRepository.save(sale);
        return sale;
    }

    @Override
    public List<Sale> getAll() {
        return saleRepository.findAll();
    }

    @Override
    public List<Sale> getAllSalesOfThisUser(Long userId) {
        return saleRepository.findAllByUserId(userId);
    }
}
