package com.acs.mercadopaginho.mercadopaginhobackend.checkout.presentation.controller;

import com.acs.mercadopaginho.mercadopaginhobackend.checkout.business.service.CheckOutService;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model.CheckOut;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.presentation.dto.CheckOutDto;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.presentation.dto.CreateCheckOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/checkout")
public class CheckOutResource {

    private CheckOutService checkOutService;


    @Autowired
    public CheckOutResource(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    @PostMapping
    public ResponseEntity createCheckOut(@RequestBody CreateCheckOutDto createCheckOutDto) {
        try {
            return new ResponseEntity<>(new CheckOutDto(checkOutService.createCheckOut(createCheckOutDto)),
                    HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCheckOut(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(new CheckOutDto(checkOutService.getCheckOut(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllCheckOut(@PathVariable Long id) {
        try {
            List<CheckOut> checkOuts = checkOutService.getAll();
            List<CheckOutDto> checkOutDtos = new ArrayList<>();
            for (CheckOut checkOut : checkOuts) {
                checkOutDtos.add(new CheckOutDto(checkOut));
            }
            return new ResponseEntity<>(checkOutDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
