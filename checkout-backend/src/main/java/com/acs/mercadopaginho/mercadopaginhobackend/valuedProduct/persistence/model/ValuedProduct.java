package com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model;

import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;

import javax.persistence.Id;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Access(AccessType.FIELD)
public class ValuedProduct {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double value;
    @ManyToOne(targetEntity = Product.class)
    private Product product;
    private LocalDateTime dateTime;


    public ValuedProduct() {
        // necesito esto para el front @Peltevis
        // OK, pro llevelo noma
    }

    public ValuedProduct(double value, Product product, LocalDateTime dateTime) {
        this.value = value;
        this.product = product;
        this.dateTime = dateTime;
    }

    // @Access(AccessType.PROPERTY)
    public double getValue() {
        return value;
    }

    // @Access(AccessType.PROPERTY)
    public Product getProduct() {
        return product;
    }

    // @Access(AccessType.PROPERTY)
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Long getId() {
        return id;
    }
}
