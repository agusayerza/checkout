package com.acs.mercadopaginho.mercadopaginhobackend.discount.persistence.model;

import java.security.InvalidParameterException;

public class Discount {

    private final double discount;

    public Discount(double discount) {
        validateData(discount);
        this.discount = discount;
    }

    private void validateData(double discount) {
        if (discount > 1 || discount <= 0) {
            throw new InvalidParameterException("The discount must be expressed as a value larger than 0 and smaller " +
                    "or equal to 1");
        }
    }

    public double apply(int value) {
        if (value <= 0) {
            throw new InvalidParameterException("The discount cannot be applied to 0 or negative values");
        }
        return value - (value * discount);
    }
}
