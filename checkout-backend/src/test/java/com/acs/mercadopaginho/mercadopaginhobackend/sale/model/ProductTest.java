package com.acs.mercadopaginho.mercadopaginhobackend.sale.model;

import com.acs.mercadopaginho.mercadopaginhobackend.DummyModelRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

    private static User user;

    private static Address address;

    @BeforeEach
    public void setUp() {
        user = DummyModelRepository.getIronMan();
        address = DummyModelRepository.getIronManAddress();
    }

    @Test
    public void test_001_ProductIsCreatedProperly() {
        user.addAddress(address);

        String productName = "ARC Reactor";

        Product product = new Product(productName, user, address);

        assertThat(product.getAddress()).isEqualTo(address);
        assertThat(product.getProductName()).isEqualTo(productName);
        assertThat(product.getUser()).isEqualTo(user);
    }

    @Test
    public void test_002_TheAddressForTheProductMustBeRegisterdAsPartOfTheUsersAddress() {
        String productName = "ARC Reactor";

        assertThatThrownBy(() -> {
            Product product = new Product(productName, user, address);
        }).isInstanceOf(InvalidParameterException.class)
          .hasMessage("The address for the product must be registered as one of the user's address");
    }

}