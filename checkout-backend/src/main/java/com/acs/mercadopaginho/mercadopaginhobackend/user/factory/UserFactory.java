package com.acs.mercadopaginho.mercadopaginhobackend.user.factory;

import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto.CreateUserDto;

public class UserFactory {
    public static User create(CreateUserDto userDto) {
        return new User(userDto.getName(), userDto.getSurname());
    }
}
