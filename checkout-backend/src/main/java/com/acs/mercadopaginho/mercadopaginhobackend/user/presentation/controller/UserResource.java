package com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.controller;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.AddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.user.business.service.UserService;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto.CreateUserDto;
import com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody CreateUserDto createUserDto) {
        try {
            return new ResponseEntity<>(new UserDto(userService.createUser(createUserDto)), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(new UserDto(userService.getUser(id)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity getAllUser() {
        try {
            List<User> userList = userService.getAll();
            List<UserDto> userDtos = new ArrayList<>();
            for (User user : userList) {
                userDtos.add(new UserDto(user));
            }
            return new ResponseEntity<>(userDtos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/me")
    public ResponseEntity getMe() {
        try {
            return new ResponseEntity<>(new UserDto(userService.getUserByName("Steve")), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/address/{addressId}")
    public ResponseEntity addAddressToUser(@PathVariable Long userId, @PathVariable Long addressId) {
        try {
            return new ResponseEntity<>(new UserDto(userService.addAddress(userId, addressId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/cart/add/{productId}")
    public ResponseEntity addProductToUserCart(@PathVariable Long userId, @PathVariable Long productId) {
        try {
            return new ResponseEntity<>(new UserDto(userService.addProduct(userId, productId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{userId}/cart/remove/{productId}")
    public ResponseEntity removeProductToUserCart(@PathVariable Long userId, @PathVariable Long productId) {
        try {
            return new ResponseEntity<>(new UserDto(userService.removeProduct(userId, productId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


}
