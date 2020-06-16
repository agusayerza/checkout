package com.acs.mercadopaginho.mercadopaginhobackend.sale.model;

import com.acs.mercadopaginho.mercadopaginhobackend.discount.persistence.model.Discount;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DiscountTest {

    @Test
    public void test_001_ADiscountIsProperlyCreated() {
        Discount discount = new Discount(.25);
        assertThat(discount.apply(100)).isEqualTo(75);
    }

    @Test
    public void test_002_ADiscountCannotBeCreatedWithAValueLargerThan1OrSmallerTo0() {

        assertThatThrownBy(() -> {
            Discount discount = new Discount(0);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("The discount must be expressed as a value larger than 0 and smaller or equal to 1");

        assertThatThrownBy(() -> {
            Discount discount = new Discount(1.5);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("The discount must be expressed as a value larger than 0 and smaller or equal to 1");

        assertThatThrownBy(() -> {
            Discount discount = new Discount(-1.5);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("The discount must be expressed as a value larger than 0 and smaller or equal to 1");

        new Discount(1);
        new Discount(.99);
        new Discount(.01);
    }

    @Test
    public void test_003_ADiscountCanOnlyBeAppliedToPositiveNonZeroValues() {
        Discount discount = new Discount(.05);

        assertThatThrownBy(() -> {
            discount.apply(0);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("The discount cannot be applied to 0 or negative values");

        assertThatThrownBy(() -> {
            discount.apply(-8);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("The discount cannot be applied to 0 or negative values");
    }

}