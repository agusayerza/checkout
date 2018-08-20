package com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.dto;

import com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto.ProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.AddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.model.ProductDelivery;

public class ProductDeliveryDto {

    private Long id;
    private double deliveryCost;
    private AddressDto toDto;
    private AddressDto fromDto;
    private ProductDto productDto;

    public ProductDeliveryDto(ProductDelivery productDelivery) {
        this.id = productDelivery.getId();
        this.deliveryCost = productDelivery.getDeliveryCost();
        this.toDto = new AddressDto(productDelivery.getTo());
        this.fromDto = new AddressDto(productDelivery.getFrom());
        this.productDto = new ProductDto(productDelivery.getProduct());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public AddressDto getToDto() {
        return toDto;
    }

    public AddressDto getFromDto() {
        return fromDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }
}
