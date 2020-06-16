package com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto;

import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.AddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto.UserDto;

public class ProductDto {

    private Long id;
    private String productName;
    private AddressDto addressDto;
    private UserDto userDto;

    public ProductDto() {
    }

    public ProductDto(Product product){
        this.id = product.getId();
        this.productName = product.getProductName();
        this.addressDto = new AddressDto(product.getAddress());
        this.userDto = new UserDto(product.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }
}
