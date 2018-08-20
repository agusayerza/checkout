package com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto;

import com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto.ProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model.ValuedProduct;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ValuedProductDto {
    private Long id;
    private double value;
    private ProductDto productDto;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime dateTime;

    public ValuedProductDto(ValuedProduct valuedProduct) {
        this.id = valuedProduct.getId();
        this.value = valuedProduct.getValue();
        this.productDto = new ProductDto(valuedProduct.getProduct());
        this.dateTime = valuedProduct.getDateTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
