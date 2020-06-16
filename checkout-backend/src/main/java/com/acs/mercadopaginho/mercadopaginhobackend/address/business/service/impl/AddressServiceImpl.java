package com.acs.mercadopaginho.mercadopaginhobackend.address.business.service.impl;

import com.acs.mercadopaginho.mercadopaginhobackend.address.business.service.AddressService;
import com.acs.mercadopaginho.mercadopaginhobackend.address.factory.AddressFactory;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.repository.AddressRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.AddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.CreateAddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.user.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;
    private UserService userService;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    @Override
    public Address getAddress(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            return address;
        }
        throw new IllegalArgumentException("Address not found");
    }

    @Override
    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address createAddress(CreateAddressDto createAddressDto) {
        Address address = AddressFactory.create(createAddressDto);
        addressRepository.save(address);
        return address;
    }

    @Override
    public List<Address> getAddressesForUser(Long userId) {
        return userService.getUser(userId).getAddresses();
    }
}
