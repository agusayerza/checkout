package com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model;

import javax.persistence.Id;

import javax.persistence.*;
import java.security.InvalidParameterException;

@Entity
@Access(AccessType.FIELD)
public class Address {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int height;
    private String streetName;
    private String cityName;
    private String province;
    private int postalCode;

    public Address() {
    }

    public Address(int height, String streetName, String cityName, String province, int postalCode) {
        validateData(height, streetName, cityName, province, postalCode);
        this.height = height;
        this.streetName = streetName;
        this.cityName = cityName;
        this.province = province;
        this.postalCode = postalCode;
    }

    private void validateData(int height, String streetName, String cityName, String province, int postalCode) {
        validateHeight(height);
        validatePostalCode(postalCode);
    }

    private void validatePostalCode(int postalCode) {
        if (!(postalCode > 0) || !((int) (Math.log10(postalCode) + 1) == 4)) {
            throw new InvalidParameterException("Postal code has to be 4 digits long and positive");
        }
    }

    private void validateHeight(int height) {
        if (height <= 0) {
            throw new InvalidParameterException("Address has to be equal or larger than 0");
        }
    }

    // @Access(AccessType.PROPERTY)
    public int getHeight() {
        return height;
    }

    // @Access(AccessType.PROPERTY)
    public String getStreetName() {
        return streetName;
    }

    // @Access(AccessType.PROPERTY)
    public String getCityName() {
        return cityName;
    }

    // @Access(AccessType.PROPERTY)
    public String getProvince() {
        return province;
    }

    // @Access(AccessType.PROPERTY)
    public int getPostalCode() {
        return postalCode;
    }

    // @Access(AccessType.PROPERTY)
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return this.height + this.postalCode + this.cityName.hashCode() + this.streetName.hashCode() + this.province.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this.hashCode() == obj.hashCode();
    }
}
