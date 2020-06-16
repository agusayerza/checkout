package com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.controller;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.AddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.CreateAddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.address.business.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressResource {

    private AddressService addressService;


    @Autowired
    public AddressResource(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity createAddress(@RequestBody CreateAddressDto createAddressDto) {
        try {
            return new ResponseEntity<>(new AddressDto(addressService.createAddress(createAddressDto)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getAddress(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(new AddressDto(addressService.getAddress(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/my/{userId}")
    public ResponseEntity getMyAddresss(@PathVariable Long userId) {
        try {
            List<Address> addressList = addressService.getAddressesForUser(userId);
            List<AddressDto> addressDtos = new ArrayList<>();
            for (Address address : addressList) {
                addressDtos.add(new AddressDto(address));
            }
            return new ResponseEntity<>(addressDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllAddress() {
        try {
            List<Address> addressList = addressService.getAll();
            List<AddressDto> addressDtos = new ArrayList<>();
            for (Address address : addressList) {
                addressDtos.add(new AddressDto(address));
            }
            return new ResponseEntity<>(addressDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
