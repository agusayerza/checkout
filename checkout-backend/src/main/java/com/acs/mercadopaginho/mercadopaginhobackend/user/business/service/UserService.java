package com.acs.mercadopaginho.mercadopaginhobackend.user.business.service;

import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto.UserDto;
import com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto.CreateUserDto;

import java.util.List;

public interface UserService {
    User getUser(Long id);

    List<User> getAll();

    User createUser(CreateUserDto createUserDto);

    User addAddress(Long userId, Long addressId);

    User getUserByName(String steve);

    User addProduct(Long userId, Long productId);

    User removeProduct(Long userId, Long productId);
}

