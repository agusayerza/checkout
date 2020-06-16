package com.acs.mercadopaginho.mercadopaginhobackend.cart.presentation.controller;

import com.acs.mercadopaginho.mercadopaginhobackend.checkout.business.service.CheckOutService;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.presentation.dto.CheckOutDto;
import com.acs.mercadopaginho.mercadopaginhobackend.cart.AddressProductsWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CheckOutService checkOutService;

    public CartController(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    @PostMapping
    public ResponseEntity createCart(@RequestBody AddressProductsWrapper addressProductsWrapper) {
        try {
            return new ResponseEntity<>(new CheckOutDto(checkOutService.createCheckOut(addressProductsWrapper.getProducts(),
                    addressProductsWrapper.getAddress())), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
