package com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.service.business.impl;

import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.factory.ValuedProductFactory;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.repository.ProductRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.service.business.ValuedProductService;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model.ValuedProduct;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.CreateValuedProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.ValuedProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.repository.ValuedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ValuedProductServiceImpl implements ValuedProductService {

    private ValuedProductRepository valuedProductRepository;
    private ProductRepository productRepository;


    @Autowired
    public ValuedProductServiceImpl(ValuedProductRepository valuedProductRepository,
                                    ProductRepository productRepository) {
        this.valuedProductRepository = valuedProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public ValuedProduct getValuedProduct(Long id) {
        Optional<ValuedProduct> optionalValuedProduct = valuedProductRepository.findById(id);
        if (optionalValuedProduct.isPresent()) {
            ValuedProduct valuedProduct = optionalValuedProduct.get();
            return valuedProduct;
        }
        throw new IllegalArgumentException("ValuedProduct not found");
    }

    @Override
    public List<ValuedProduct> getAll() {
        return valuedProductRepository.findAll();
    }

    @Override
    public ValuedProduct createValuedProduct(CreateValuedProductDto createValuedProductDto) {
        Optional<Product> optionalProduct = productRepository.findById(createValuedProductDto.getProductId());
        if (!optionalProduct.isPresent()) throw new IllegalArgumentException("Invalid Product");
        Product product = optionalProduct.get();
        ValuedProduct valuedProduct = ValuedProductFactory.create(createValuedProductDto, product);
        valuedProductRepository.save(valuedProduct);
        return valuedProduct;
    }

    @Override
    public ValuedProduct cloneValuedProduct(ValuedProductDto ogValuedProduct) {
        CreateValuedProductDto createValuedProductDto = new CreateValuedProductDto(ogValuedProduct.getValue(),
                ogValuedProduct.getId());
        Optional<Product> optionalProduct = productRepository.findById(ogValuedProduct.getProductDto().getId());
        if (!optionalProduct.isPresent()) throw new IllegalArgumentException("Invalid Product");
        Product product = optionalProduct.get();
        ValuedProduct valuedProduct = ValuedProductFactory.create(createValuedProductDto, product);
        valuedProductRepository.save(valuedProduct);
        return valuedProduct;
    }

    @Override
    public ValuedProduct searchByName(String name) {
        Optional<Product> optionalProduct = productRepository.findFirstByProductNameContaining(name);
        if (!optionalProduct.isPresent()) throw new IllegalArgumentException("Invalid Product");
        Optional<ValuedProduct> optionalValuedProduct =
                valuedProductRepository.findFirstByProductId(optionalProduct.get().getId());
        if (optionalValuedProduct.isPresent()) {
            ValuedProduct valuedProduct = optionalValuedProduct.get();
            return valuedProduct;
        }
        throw new IllegalArgumentException("ValuedProduct not found");
    }

    @Override
    public ValuedProduct searchLatestByProductId(Long id) {
        List<ValuedProduct> all = valuedProductRepository.findAllByProductIdOrderByDateTimeDesc(id);
        if (all.size() == 0) throw new IllegalArgumentException("ValuedProduct not found");
        ValuedProduct valuedProduct = all.get(0);
        return valuedProduct;
    }
}
