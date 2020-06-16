package com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto;

public class MPPayerDto {
    private String email;

    public MPPayerDto() {
    }

    public MPPayerDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

}
