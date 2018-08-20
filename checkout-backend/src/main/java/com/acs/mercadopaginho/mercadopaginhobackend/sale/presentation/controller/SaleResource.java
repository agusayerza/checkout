package com.acs.mercadopaginho.mercadopaginhobackend.sale.presentation.controller;

import com.acs.mercadopaginho.mercadopaginhobackend.mercadoPago.dto.MPPaymentDto;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.business.service.SaleService;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.persistence.model.Sale;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.presentation.dto.SaleDto;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SaleResource {

    private SaleService saleService;

    @Autowired
    public SaleResource(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity createSale(@RequestBody MPPaymentDto mpPaymentDto) {
        try {
            return new ResponseEntity<>(new SaleDto(saleService.validateAndCreate(mpPaymentDto)), HttpStatus.OK);
        } catch (IllegalArgumentException | MPException e) {
            // Es importante que responda Ok, sino no se puede enviar body
            return new ResponseEntity<>(new SaleDto(false, e.getMessage()), HttpStatus.OK);
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllSales() {
        try {
            List<Sale> addressList = saleService.getAll();
            List<SaleDto> saleDtos = new ArrayList<>();
            for (Sale sale : addressList) {
                saleDtos.add(new SaleDto(sale));
            }
            return new ResponseEntity<>(saleDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity getAllSalesOfThisUser(@PathVariable Long userId) {
        try {
            List<Sale> addressList = saleService.getAllSalesOfThisUser(userId);
            List<SaleDto> saleDtos = new ArrayList<>();
            for (Sale sale : addressList) {
                saleDtos.add(new SaleDto(sale));
            }
            return new ResponseEntity<>(saleDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
