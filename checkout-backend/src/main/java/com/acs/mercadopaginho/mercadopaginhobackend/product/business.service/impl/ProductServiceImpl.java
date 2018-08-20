package com.acs.mercadopaginho.mercadopaginhobackend.product.business.service.impl;

import com.acs.mercadopaginho.mercadopaginhobackend.product.business.service.ProductService;
import com.acs.mercadopaginho.mercadopaginhobackend.product.factory.ProductFactory;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.repository.AddressRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.repository.ProductRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.repository.UserRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto.ProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto.CreateProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private AddressRepository addressRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository,
                              AddressRepository addressRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public Product getProduct(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return product;
        }
        throw new IllegalArgumentException("Product not found");
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product createProduct(CreateProductDto createProductDto) {
        Optional<User> optionalUser = userRepository.findById(createProductDto.getUserId());
        Optional<Address> optionalAddress = addressRepository.findById(createProductDto.getAddressId());
        if (!optionalUser.isPresent()) throw new IllegalArgumentException("Invalid User");
        if (!optionalAddress.isPresent()) throw new IllegalArgumentException("Invalid Address");
        User user = optionalUser.get();
        Address address = optionalAddress.get();
        if (!user.getAddresses().contains(address)) throw new IllegalArgumentException("Address not from user");
        Product product = ProductFactory.create(createProductDto, user, address);
        productRepository.save(product);
        return product;
    }
}
