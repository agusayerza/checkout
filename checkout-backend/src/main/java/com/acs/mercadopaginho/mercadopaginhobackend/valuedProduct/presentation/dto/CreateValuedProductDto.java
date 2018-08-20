package com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto;

import org.springframework.format.annotation.DateTimeFormat;

public class CreateValuedProductDto {

    private double value;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Long productId;

    public CreateValuedProductDto(double value, Long productId) {
        this.value = value;
        this.productId = productId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
