package com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto;

public class CreateAddressDto {

    private int height;
    private String streetName;
    private String cityName;
    private String province;
    private int postalCode;

    public CreateAddressDto(int height, String streetName, String cityName, String province, int postalCode) {
        this.height = height;
        this.streetName = streetName;
        this.cityName = cityName;
        this.province = province;
        this.postalCode = postalCode;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

}
