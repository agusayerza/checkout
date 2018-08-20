package com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.controller;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.AddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model.ValuedProduct;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.ValuedProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.service.business.ValuedProductService;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.CreateValuedProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/valued-products")
public class ValuedProductResource {

    private ValuedProductService valuedProductService;

    @Autowired
    public ValuedProductResource(ValuedProductService valuedProductService) {
        this.valuedProductService = valuedProductService;
    }

    @PostMapping
    public ResponseEntity createValuedProduct(@RequestBody CreateValuedProductDto createValuedProductDto) {
        try {
            return new ResponseEntity<>(new ValuedProductDto(valuedProductService.createValuedProduct(createValuedProductDto)),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getValuedProduct(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(new ValuedProductDto(valuedProductService.getValuedProduct(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllValuedProduct() {
        try {
            List<ValuedProduct> all = valuedProductService.getAll();
            List<ValuedProductDto> valuedProductDtos = new ArrayList<>();
            for (ValuedProduct valuedProduct : all) {
                valuedProductDtos.add(new ValuedProductDto(valuedProduct));
            }
            return new ResponseEntity<>(valuedProductDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity getAllValuedProduct(@PathVariable String name) {
        try {
            return new ResponseEntity<>(new ValuedProductDto(valuedProductService.searchByName(name)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
