package com.acs.mercadopaginho.mercadopaginhobackend.sale.model;

import com.acs.mercadopaginho.mercadopaginhobackend.cart.persistence.model.Cart;
import com.acs.mercadopaginho.mercadopaginhobackend.DummyModelRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CartTest {

    private static User user;

    private static Address address;

    private static Product product;

    @BeforeEach
    public void setUp() {
        user = DummyModelRepository.getIronMan();
        address = DummyModelRepository.getIronManAddress();
        user.addAddress(address);
        String productName = "ARC Reactor";
        product = new Product(productName, user, address);
    }

    @Test
    public void test_001_ACartIsCreatedEmpty() {
        Cart cart = new Cart();
        assertThat(cart.getProducts()).isEmpty();
    }

    @Test
    public void test_002_WhenAddingAProductToTheCartItRemains() {
        Cart cart = new Cart();
        cart.add(product);
        assertThat(cart.getProducts()).isNotEmpty().containsExactly(product);
    }

    @Test
    public void test_003_WhenDeletingAProductToTheCartItIsRemoved() {
        Cart cart = new Cart();
        cart.add(product);
        assertThat(cart.getProducts()).isNotEmpty().containsExactly(product);
        cart.remove(product);
        assertThat(cart.getProducts()).isEmpty();
    }

    @Test
    public void test_004_WhenAddingTheSameElementItIsAdded(){
        Cart cart = new Cart();
        cart.add(product);
        cart.add(product);
        assertThat(cart.getProducts()).isNotEmpty().containsExactly(product, product);
    }


}