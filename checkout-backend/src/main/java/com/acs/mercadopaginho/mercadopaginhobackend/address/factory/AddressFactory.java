package com.acs.mercadopaginho.mercadopaginhobackend.address.factory;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.AddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.CreateAddressDto;

import java.util.ArrayList;
import java.util.List;


public class AddressFactory {

    public static Address create(CreateAddressDto addressDto) {
        return new Address(addressDto.getHeight(), addressDto.getStreetName(),
                addressDto.getCityName(), addressDto.getProvince(), addressDto.getPostalCode());
    }

    public static List<AddressDto> getAddressesDtoList(List<Address> addresses) {
        ArrayList<AddressDto> addressDtoList = new ArrayList<>();
        addresses.forEach((address -> addressDtoList.add(new AddressDto(address))));
        return addressDtoList;
    }
}
