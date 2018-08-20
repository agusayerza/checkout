package com.acs.mercadopaginho.mercadopaginhobackend.address.business.service;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.AddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.CreateAddressDto;

import java.util.List;

public interface AddressService {
    Address getAddress(Long id);
    List<Address> getAll();
    Address createAddress(CreateAddressDto createAddressDto);
    List<Address> getAddressesForUser(Long id);
}
