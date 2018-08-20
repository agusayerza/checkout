package com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.controller;

import com.acs.mercadopaginho.mercadopaginhobackend.product.business.service.ProductService;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto.CreateProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductResource {

    private ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody CreateProductDto createProductDto) {
        try {
            return new ResponseEntity<>(new ProductDto(productService.createProduct(createProductDto)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(new ProductDto(productService.getProduct(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllProduct() {
        try {
            List<Product> addressList = productService.getAll();
            List<ProductDto> productDtos = new ArrayList<>();
            for (Product product : addressList) {
                productDtos.add(new ProductDto(product));
            }
            return new ResponseEntity<>(productDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
