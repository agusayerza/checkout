package com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;

public class AddressDto {
    private Long id;
    private int height;
    private String streetName;
    private String cityName;
    private String province;
    private int postalCode;

    public AddressDto() {
    }

    public AddressDto(Address address) {
        this.id = address.getId();
        this.height = address.getHeight();
        this.streetName = address.getStreetName();
        this.cityName = address.getCityName();
        this.province = address.getProvince();
        this.postalCode = address.getPostalCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHeight() {
        return height;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getProvince() {
        return province;
    }

    public int getPostalCode() {
        return postalCode;
    }
}
