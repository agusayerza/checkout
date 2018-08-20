package com.acs.mercadopaginho.mercadopaginhobackend.checkout.presentation.dto;


import java.util.List;

public class CreateCheckOutDto {
    private List<Long> productDeliveryIdList;
    private List<Long> productIdList;

    public CreateCheckOutDto(List<Long> productDeliveryIdList, List<Long> productIdList) {
        this.productDeliveryIdList = productDeliveryIdList;
        this.productIdList = productIdList;
    }

    public List<Long> getProductDeliveryIdList() {
        return productDeliveryIdList;
    }

    public List<Long> getProductIdList() {
        return productIdList;
    }
}
