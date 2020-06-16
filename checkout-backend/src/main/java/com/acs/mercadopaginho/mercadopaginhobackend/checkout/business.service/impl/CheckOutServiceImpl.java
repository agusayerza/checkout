package com.acs.mercadopaginho.mercadopaginhobackend.checkout.business.service.impl;

import com.acs.mercadopaginho.mercadopaginhobackend.address.business.service.AddressService;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.business.service.CheckOutService;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.factory.CheckOutFactory;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model.CheckOut;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.repository.CheckOutRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.presentation.dto.CreateCheckOutDto;
import com.acs.mercadopaginho.mercadopaginhobackend.common.IdDto;
import com.acs.mercadopaginho.mercadopaginhobackend.datetime.DateTimeService;
import com.acs.mercadopaginho.mercadopaginhobackend.product.business.service.ProductService;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.repository.ProductRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.business.service.ProductDeliveryService;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.model.ProductDelivery;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.repository.ProductDeliveryRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.dto.CreateProductDeliveryDto;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import com.acs.mercadopaginho.mercadopaginhobackend.valuator.ValuatorService;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model.ValuedProduct;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.CreateValuedProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.service.business.ValuedProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CheckOutServiceImpl implements CheckOutService {

    private final ProductService productService;
    private final AddressService addressService;
    private CheckOutRepository checkOutRepository;
    private ProductDeliveryRepository productDeliveryRepository;
    private ProductRepository productRepository;
    private ProductDeliveryService externalDeliveryCalculator;
    private ValuatorService valuatorService;
    private DateTimeService dateTimeService;
    private ValuedProductService valuedProductService;

    @Autowired
    public CheckOutServiceImpl(CheckOutRepository checkOutRepository,
                               ProductDeliveryRepository productDeliveryRepository,
                               ProductRepository productRepository,
                               ProductService productService,
                               ProductDeliveryService deliveryService,
                               ValuatorService valuatorService,
                               DateTimeService dateTimeService,
                               AddressService addressService,
                               ValuedProductService valuedProductService) {
        this.checkOutRepository = checkOutRepository;
        this.productRepository = productRepository;
        this.productDeliveryRepository = productDeliveryRepository;
        this.externalDeliveryCalculator = deliveryService;
        this.valuatorService = valuatorService;
        this.dateTimeService = dateTimeService;
        this.productService = productService;
        this.addressService = addressService;
        this.valuedProductService = valuedProductService;
    }

    @Override
    public CheckOut getCheckOut(Long id) {
        Optional<CheckOut> optionalCheckOut = checkOutRepository.findById(id);
        if (optionalCheckOut.isPresent()) {
            CheckOut checkOut = optionalCheckOut.get();
            return checkOut;
        }
        throw new IllegalArgumentException("CheckOut not found");
    }

    @Override
    public List<CheckOut> getAll() {
        return checkOutRepository.findAll();
    }

    @Override
    public CheckOut createCheckOut(List<Long> productDeliveryIdList, List<Long> productIdList) {
        List<ProductDelivery> productDeliveries =
                productDeliveryRepository.findAllById(productDeliveryIdList);
        List<Product> productList =
                productRepository.findAllById(productIdList);
        if (productDeliveries.size() != productDeliveryIdList.size())
            throw new IllegalArgumentException("Some ProductDeliveries not found");
        if (productList.size() != productList.size())
            throw new IllegalArgumentException("Some ValuedProducts not found");
        List<ValuedProduct> valuedProductList = new ArrayList<>();
        productList.forEach(product -> {
            valuedProductList.add(valuedProductService.createValuedProduct(new CreateValuedProductDto(valuatorService.value(product), product.getId())));
        });
        CheckOut checkOut = CheckOutFactory.create(productDeliveries, valuedProductList);
        checkOutRepository.save(checkOut);
        return checkOut;
    }

    @Override
    public CheckOut createCheckOut(List<IdDto> productIdDtos, IdDto addressIdDto) {
        List<Product> products = getProducts(productIdDtos);
        Address address = addressService.getAddress(addressIdDto.getId());
        List<ProductDelivery> productDeliveries = calculateDeliveryCost(products, address);
        List<ValuedProduct> valuedProducts = valueCart(products);
        return checkOutRepository.save(CheckOutFactory.create(productDeliveries, valuedProducts));
    }

    private List<Product> getProducts(List<IdDto> products) {
        List<Product> productList = new ArrayList<>();
        for (IdDto product : products) {
            productList.add(productService.getProduct(product.getId()));
        }
        return productList;
    }

    @Override
    public CheckOut createCheckOut(CreateCheckOutDto createCheckOutDto) {
        return createCheckOut(createCheckOutDto.getProductDeliveryIdList(), createCheckOutDto.getProductIdList());
    }

    @Override
    public CheckOut checkout(User buyer) {
        List<ProductDelivery> productDeliveries = calculateDeliveryCost(buyer.getCurrentCart().getProducts(),
                buyer.getCurrentAddress());
        List<ValuedProduct> valuedProducts = valueCart(buyer.getCurrentCart().getProducts());
        return checkOutRepository.save(CheckOutFactory.create(productDeliveries, valuedProducts));
    }

    private List<ValuedProduct> valueCart(List<Product> products) {
        List<ValuedProduct> valuedProducts = new ArrayList<>();
        for (Product product : products) {
            double value = valuatorService.value(product);
            valuedProducts.add(valuedProductService.createValuedProduct(new CreateValuedProductDto(value, product.getId())));
        }
        return valuedProducts;
    }

    private List<ProductDelivery> calculateDeliveryCost(List<Product> products, Address to) {
        List<ProductDelivery> productDeliveries = new ArrayList<>();
        for (Product product : products) {
            Address from = product.getAddress();
            double deliveryCost = externalDeliveryCalculator.calculateCost(to, from);
            productDeliveries.add(externalDeliveryCalculator.createProductDelivery(new CreateProductDeliveryDto(deliveryCost,to.getId(), product.getAddress().getId(), product.getId())));
        }
        return productDeliveries;
    }
}
