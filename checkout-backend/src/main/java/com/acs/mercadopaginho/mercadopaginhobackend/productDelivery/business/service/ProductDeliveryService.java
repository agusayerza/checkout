package com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.business.service;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.model.ProductDelivery;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.dto.CreateProductDeliveryDto;

import java.util.List;

public interface ProductDeliveryService {
    ProductDelivery getProductDelivery(Long id);

    List<ProductDelivery> getAll();

    ProductDelivery createProductDelivery(CreateProductDeliveryDto createProductDeliveryDto);

    double calculateCost(Address to, Address from);
}
