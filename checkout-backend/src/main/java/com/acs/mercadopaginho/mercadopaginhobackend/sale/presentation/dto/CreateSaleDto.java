package com.acs.mercadopaginho.mercadopaginhobackend.sale.presentation.dto;

public class CreateSaleDto {

    private Long userId;
    private Long checkOutId;

    public CreateSaleDto(Long userId, Long checkOutId) {
        this.userId = userId;
        this.checkOutId = checkOutId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCheckOutId() {
        return checkOutId;
    }
}
