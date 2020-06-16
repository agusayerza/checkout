package com.acs.mercadopaginho.mercadopaginhobackend.checkout.presentation.dto;

import com.acs.mercadopaginho.mercadopaginhobackend.checkout.factory.CheckOutFactory;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.factory.ProductDeliveryFactory;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model.CheckOut;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.dto.ProductDeliveryDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.ValuedProductDto;

import java.util.List;

public class CheckOutDto {
    private Long id;
    private List<ProductDeliveryDto> productDeliveryDtoList;
    private List<ValuedProductDto> valuedProductDtosList;

    public CheckOutDto(CheckOut checkOut) {
        this.id = checkOut.getId();
        this.productDeliveryDtoList = ProductDeliveryFactory.getProductDeliveryDtoList(checkOut.getProductDeliveries());
        this.valuedProductDtosList = CheckOutFactory.getCheckOutDtoList(checkOut.getValuedProducts());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductDeliveryDto> getProductDeliveryDtoList() {
        return productDeliveryDtoList;
    }

    public List<ValuedProductDto> getValuedProductDtosList() {
        return valuedProductDtosList;
    }

}
