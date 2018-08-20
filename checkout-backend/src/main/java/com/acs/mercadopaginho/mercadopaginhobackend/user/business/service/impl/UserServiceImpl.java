package com.acs.mercadopaginho.mercadopaginhobackend.user.business.service.impl;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.repository.AddressRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.cart.service.CartService;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.repository.ProductRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.user.business.service.UserService;
import com.acs.mercadopaginho.mercadopaginhobackend.user.factory.UserFactory;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.repository.UserRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto.CreateUserDto;
import com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private ProductRepository productRepository;
    private CartService cartService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, AddressRepository addressRepository, ProductRepository productRepository, CartService cartService) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
        this.cartService = cartService;
    }

    @Override
    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return user;
        }
        throw new IllegalArgumentException("User not found");
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(CreateUserDto createUserDto) {
        User user = UserFactory.create(createUserDto);
        userRepository.save(user);
        return user;
    }

    @Override
    public User addAddress(Long userId, Long addressId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) throw new IllegalArgumentException("User not found");
        Optional<Address> optionalAddress = addressRepository.findById(addressId);
        if (!optionalAddress.isPresent()) throw new IllegalArgumentException("Address not found");
        User user = optionalUser.get();
        user.addAddress(optionalAddress.get());
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUserByName(String name) {
        Optional<User> optionalUser = userRepository.findFirstByName(name);
        if (!optionalUser.isPresent()) throw new IllegalArgumentException("User not found");
        User user = optionalUser.get();
        return user;
    }

    @Override
    public User addProduct(Long userId, Long productId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) throw new IllegalArgumentException("User not found");
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) throw new IllegalArgumentException("Product not found");
        User user = optionalUser.get();
//        user.addToCart(optionalProduct.get());
        cartService.getCartForUser(userId).add(optionalProduct.get());
        userRepository.save(user);

        return user;
    }

    @Override
    public User removeProduct(Long userId, Long productId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) throw new IllegalArgumentException("User not found");
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()) throw new IllegalArgumentException("Product not found");
        User user = optionalUser.get();
        cartService.getCartForUser(userId).remove(optionalProduct.get());
        userRepository.save(user);
        return user;
    }
}
