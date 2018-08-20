package com.acs.mercadopaginho.mercadopaginhobackend.sale.business.service;

import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto.MPPaymentDto;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.persistence.model.Sale;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.presentation.dto.CreateSaleDto;
import com.mercadopago.exceptions.MPException;

import java.util.List;

public interface SaleService {
    Sale validateAndCreate(MPPaymentDto mpPaymentDto) throws MPException;
    Sale create(CreateSaleDto createSaleDto);
    List<Sale> getAll();
    List<Sale> getAllSalesOfThisUser(Long userId);
}
