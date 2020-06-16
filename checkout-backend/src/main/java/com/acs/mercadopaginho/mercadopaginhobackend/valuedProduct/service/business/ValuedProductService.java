package com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.service.business;

import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model.ValuedProduct;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.CreateValuedProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.ValuedProductDto;

import java.util.List;

public interface ValuedProductService {
    ValuedProduct getValuedProduct(Long id);
    List<ValuedProduct> getAll();
    ValuedProduct createValuedProduct(CreateValuedProductDto createValuedProductDto);
    ValuedProduct cloneValuedProduct(ValuedProductDto ogValuedProduct);
    ValuedProduct searchByName(String name);
    ValuedProduct searchLatestByProductId(Long id);
}
