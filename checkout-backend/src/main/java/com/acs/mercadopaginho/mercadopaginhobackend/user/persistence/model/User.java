package com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.cart.persistence.model.Cart;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;

import javax.persistence.Id;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "\"User\"")
public class User {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String surname;
    private String name;
    @ElementCollection(targetClass= Address.class)
    private List<Address> addresses;
    @Transient
    private Cart currentCart = new Cart();
    @Transient
    private Address selectedAddress;

    public User() {
    }

    public User(String name, String surname) {
        validateData(name, surname);
        this.name = name;
        this.surname = surname;
        this.addresses = new ArrayList<Address>();
        this.selectedAddress = null;
        this.currentCart = new Cart();
    }

    private void validateData(String name, String surname) {
        validateSurname(surname);
        validateName(name);
    }

    private void validateName(String name) {
        if (!name.matches("[a-zA-Z]+")) {
            throw new InvalidParameterException("User name must be alphabetic");
        }
    }

    private void validateSurname(String surname) {
        if (!surname.matches("[a-zA-Z]+")) {
            throw new InvalidParameterException("User surname must be alphabetic");
        }
    }

    // @Access(AccessType.PROPERTY)
    public String getName() {
        return name;
    }

    // @Access(AccessType.PROPERTY)
    public String getSurname() {
        return surname;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void addAddress(Address address) {
        addresses.add(address);
        this.selectedAddress = address;
    }

    public void addToCart(Product product) {
        currentCart.add(product);
    }

    public void removeFromCart(Product product) {
        currentCart.remove(product);
    }

    public Cart getCurrentCart() {
        return currentCart;
    }

    public Address getCurrentAddress() {
        return selectedAddress;
    }

    public Long getId() {
        return id;
    }

    public Address getSelectedAddress() {
        return selectedAddress;
    }
}
