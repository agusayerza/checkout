package com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.util.Objects;

@Entity
@Access(AccessType.FIELD)
public class Product {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;
    @ManyToOne(targetEntity = Address.class)
    private Address address;
    @ManyToOne(targetEntity = User.class)
    private User user;

    public Product() {
    }

    public Product(String productName, User user, Address address) {
        validateData(productName, user, address);
        this.productName = productName;
        this.user = user;
        this.address = address;
    }

    // @Access(AccessType.PROPERTY)
    public String getProductName() {
        return productName;
    }

    // @Access(AccessType.PROPERTY)
    public Address getAddress() {
        return address;
    }

    // @Access(AccessType.PROPERTY)
    public User getUser() {
        return user;
    }

    // @Access(AccessType.PROPERTY)
    public Long getId() {
        return id;
    }

    private void validateData(String productName, User user, Address address) {
        validateUserHasAddress(user, address);
    }

    private void validateUserHasAddress(User user, Address address) {
        if (!user.getAddresses().contains(address)) {
            throw new InvalidParameterException("The address for the product must be registered as one of the user's " +
                    "address");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                productName.equals(product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName);
    }
}
