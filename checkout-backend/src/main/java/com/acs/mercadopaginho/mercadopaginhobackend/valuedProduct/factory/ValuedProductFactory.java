package com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.factory;

import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model.ValuedProduct;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.CreateValuedProductDto;

import java.time.LocalDateTime;


public class ValuedProductFactory {
    public static ValuedProduct create(CreateValuedProductDto valuedProductDto, Product product) {
        return new ValuedProduct(valuedProductDto.getValue(), product, LocalDateTime.now());
    }
}
