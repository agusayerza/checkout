package com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.model;

import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;

import javax.persistence.Id;

import javax.persistence.*;

@Entity
@Access(AccessType.FIELD)
public class ProductDelivery {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double deliveryCost;
    @ManyToOne(targetEntity = Address.class)
    private Address to;
    @ManyToOne(targetEntity = Address.class)
    private Address from;
    @ManyToOne(targetEntity = Product.class)
    private Product product;

    public ProductDelivery(double deliveryCost, Address to, Address from, Product product) {
        this.deliveryCost = deliveryCost;
        this.to = to;
        this.from = from;
        this.product = product;
    }

    // @Access(AccessType.PROPERTY)
    public double getDeliveryCost() {
        return deliveryCost;
    }

    // @Access(AccessType.PROPERTY)
    public Address getTo() {
        return to;
    }

    // @Access(AccessType.PROPERTY)
    public Address getFrom() {
        return from;
    }

    // @Access(AccessType.PROPERTY)
    public Product getProduct() {
        return product;
    }

    public Long getId() {
        return id;
    }


    public ProductDelivery() {
    }

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setDeliveryCost(double deliveryCost) {
//        this.deliveryCost = deliveryCost;
//    }
//
//    public void setTo(Address to) {
//        this.to = to;
//    }
//
//    public void setFrom(Address from) {
//        this.from = from;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
}
