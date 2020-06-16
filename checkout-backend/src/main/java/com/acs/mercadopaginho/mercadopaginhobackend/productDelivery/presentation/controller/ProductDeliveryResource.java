package com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.controller;

import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.business.service.ProductDeliveryService;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.dto.CreateProductDeliveryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class ProductDeliveryResource {

    private ProductDeliveryService productDeliveryService;

    @Autowired
    public ProductDeliveryResource(ProductDeliveryService productDeliveryService) {
        this.productDeliveryService = productDeliveryService;
    }

    @PostMapping
    public ResponseEntity createProductDelivery(@RequestBody CreateProductDeliveryDto createProductDeliveryDto){
        try{
            return new ResponseEntity<>(productDeliveryService.createProductDelivery(createProductDeliveryDto), HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getProductDelivery(@PathVariable Long id){
        try{
            return new ResponseEntity<>(productDeliveryService.getProductDelivery(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllProductDelivery() {
        try {
            return new ResponseEntity<>(productDeliveryService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
