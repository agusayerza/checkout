package com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto;

public class CreateProductDto {

    private String productName;
    private Long addressId;
    private Long userId;

    public CreateProductDto(String productName, Long addressId, Long userId) {
        this.productName = productName;
        this.addressId = addressId;
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getAddressId() {
        return addressId;
    }

    public Long getUserId() {
        return userId;
    }
}
