package com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto;

import com.acs.mercadopaginho.mercadopaginhobackend.address.factory.AddressFactory;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.AddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;

import java.util.List;

public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private List<AddressDto> addresses;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.addresses = AddressFactory.getAddressesDtoList(user.getAddresses());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }
}