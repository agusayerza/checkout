package com.acs.mercadopaginho.mercadopaginhobackend.product.factory;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto.CreateProductDto;


public class ProductFactory {
    public static Product create(CreateProductDto productDto, User user, Address address) {
        return new Product(productDto.getProductName(), user, address);
    }
}
