package com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model;

import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.model.ProductDelivery;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model.ValuedProduct;

import javax.persistence.*;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
public class CheckOut {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @ElementCollection(targetClass= ProductDelivery.class)
    private List<ProductDelivery> productDeliveries;
    @ManyToMany(cascade = CascadeType.ALL)
    @ElementCollection(targetClass=ValuedProduct.class)
    private List<ValuedProduct> valuedProducts;

    public CheckOut(List<ProductDelivery> productDeliveries, List<ValuedProduct> valuedProducts) {
        this.productDeliveries = productDeliveries;
        this.valuedProducts = valuedProducts;
    }

    public CheckOut() {
        //@peltevis
    }

    // @Access(AccessType.PROPERTY)
    public List<ProductDelivery> getProductDeliveries() {
        return productDeliveries;
    }

    // @Access(AccessType.PROPERTY)
    public List<ValuedProduct> getValuedProducts() {
        return valuedProducts;
    }

    public Long getId() {
        return id;
    }
}
