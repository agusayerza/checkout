package com.acs.mercadopaginho.mercadopaginhobackend.product.business.service;

import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto.ProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto.CreateProductDto;

import java.util.List;

public interface ProductService {
    Product getProduct(Long id);

    List<Product> getAll();

    Product createProduct(CreateProductDto createProductDto);
}
