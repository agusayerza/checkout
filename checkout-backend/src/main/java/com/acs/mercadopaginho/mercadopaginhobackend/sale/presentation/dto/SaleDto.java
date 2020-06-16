package com.acs.mercadopaginho.mercadopaginhobackend.sale.presentation.dto;

import com.acs.mercadopaginho.mercadopaginhobackend.sale.persistence.model.Sale;

import java.time.LocalDateTime;

public class SaleDto {

    private Long id;
    private Long userId;
    private Long checkOutId;
    private LocalDateTime localDateTime;
    private boolean success = false;
    private String message = "";

    public SaleDto(Sale sale) {
        this.id = sale.getId();
        this.userId = sale.getUser().getId();
        this.checkOutId = sale.getCheckOut().getId();
        this.localDateTime = sale.getLocalDateTime();
        this.success = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getCheckOutId() {
        return checkOutId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public SaleDto(boolean success, String message) {
        this.id =-1l;
        this.success = success;
        this.message = message;
    }
}
