package com.acs.mercadopaginho.mercadopaginhobackend.cart.persistence.model;

import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void add(Product product) {
        this.products.add(product);
    }

    public void remove(Product product) {
        if(!products.contains(product)) throw new IllegalArgumentException("Product was not in cart");
        products.remove(product);
    }
}
