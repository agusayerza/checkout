package com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.dto;

public class CreateProductDeliveryDto {

    private double deliveryCost;
    private Long toId;
    private Long fromId;
    private Long productId;

    public CreateProductDeliveryDto(double deliveryCost, Long toId, Long fromId, Long productId) {
        this.deliveryCost = deliveryCost;
        this.toId = toId;
        this.fromId = fromId;
        this.productId = productId;
    }

    public double getDeliveryCost() {
        return deliveryCost;
    }

    public Long getToId() {
        return toId;
    }

    public Long getFromId() {
        return fromId;
    }

    public Long getProductId() {
        return productId;
    }

    public CreateProductDeliveryDto() {
    }
}
