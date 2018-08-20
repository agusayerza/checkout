package com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.business.service.impl;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.repository.AddressRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.business.service.ProductDeliveryService;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.repository.ProductRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.factory.ProductDeliveryFactory;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.model.ProductDelivery;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.repository.ProductDeliveryRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.dto.CreateProductDeliveryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductDeliveryServiceImpl implements ProductDeliveryService {

    private ProductDeliveryRepository productDeliveryRepository;
    private AddressRepository addressRepository;
    private ProductRepository productRepository;

    @Autowired
    public ProductDeliveryServiceImpl(ProductDeliveryRepository productDeliveryRepository,
                                      AddressRepository addressRepository,
                                      ProductRepository productRepository) {
        this.productDeliveryRepository = productDeliveryRepository;
        this.addressRepository = addressRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDelivery getProductDelivery(Long id) {
        Optional<ProductDelivery> optionalProductDelivery = productDeliveryRepository.findById(id);
        if (optionalProductDelivery.isPresent()) {
            ProductDelivery productDelivery = optionalProductDelivery.get();
            return productDelivery;
        }
        throw new IllegalArgumentException("ProductDelivery not found");
    }

    @Override
    public List<ProductDelivery> getAll() {
        return productDeliveryRepository.findAll();
    }

    @Override
    public ProductDelivery createProductDelivery(CreateProductDeliveryDto createProductDeliveryDto) {
        Optional<Address> optionalTo = addressRepository.findById(createProductDeliveryDto.getFromId());
        Optional<Address> optionalFrom = addressRepository.findById(createProductDeliveryDto.getToId());
        Optional<Product> optionalProduct = productRepository.findById(createProductDeliveryDto.getProductId());
        if (!optionalTo.isPresent()) throw new IllegalArgumentException("Invalid To Address");
        if (!optionalFrom.isPresent()) throw new IllegalArgumentException("Invalid From Address");
        if (!optionalProduct.isPresent()) throw new IllegalArgumentException("Invalid Product");
        Address from = optionalFrom.get();
        Address to = optionalTo.get();
        Product product = optionalProduct.get();
        ProductDelivery productDelivery = ProductDeliveryFactory.create(createProductDeliveryDto, to, from, product);
        productDeliveryRepository.save(productDelivery);
        return productDelivery;
    }

    @Override
    public double calculateCost(Address to, Address from) {
        return 50;
    }
}
