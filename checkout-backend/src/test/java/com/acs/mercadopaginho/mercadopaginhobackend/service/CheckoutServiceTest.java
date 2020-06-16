package com.acs.mercadopaginho.mercadopaginhobackend.service;

import com.acs.mercadopaginho.mercadopaginhobackend.address.business.service.AddressService;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model.CheckOut;
import com.acs.mercadopaginho.mercadopaginhobackend.product.business.service.ProductService;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.datetime.DateTimeService;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.repository.ProductRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.business.service.ProductDeliveryService;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.dto.CreateProductDeliveryDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuator.ValuatorService;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.business.service.impl.CheckOutServiceImpl;
import com.acs.mercadopaginho.mercadopaginhobackend.DummyModelRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.repository.CheckOutRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.repository.ProductDeliveryRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model.ValuedProduct;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.model.ProductDelivery;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.CreateValuedProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.service.business.ValuedProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class CheckoutServiceTest {

    private static User seller;
    private static Address sellerAddress;
    private static User buyer;
    private static Address buyerAddress;
    private static Product product;
    private static ProductDeliveryService deliveryService;
    private static ValuatorService valuatorService;
    private static DateTimeService dateTimeService;
    private static ValuedProductService valuedProductService;
    private static CheckOutRepository checkOutRepository;

    @BeforeAll
    public static void globalSetUp() {
        seller = DummyModelRepository.getIronMan();
        buyer = DummyModelRepository.getCaptainAmerica();

        sellerAddress = DummyModelRepository.getIronManAddress();
        buyerAddress = DummyModelRepository.getCaptainAmericaAddress();

        seller.addAddress(sellerAddress);
        buyer.addAddress(buyerAddress);

        String productName = "Vibranium Shield";
        product = new Product(productName, seller, DummyModelRepository.getIronManAddress());

        ProductDeliveryService mockDeliveryService = mock(ProductDeliveryService.class);
        ValuatorService mockValuatorService = mock(ValuatorService.class);
        DateTimeService mockDateTimeService = mock(DateTimeService.class);
        ValuedProductService mockValuedProductService = mock(ValuedProductService.class);
        CheckOutRepository mockCheckOutRepository = mock(CheckOutRepository.class);

        when(mockDeliveryService.calculateCost(any(Address.class), any(Address.class))).thenReturn(50.0);

        ProductDelivery productDelivery = new ProductDelivery(50.0, buyerAddress, sellerAddress, product);
        when(mockDeliveryService.createProductDelivery(any(CreateProductDeliveryDto.class)))
                .thenReturn(productDelivery);

        when(mockValuatorService.value(any(Product.class))).thenReturn(100.0);

        ValuedProduct valuedProduct = new ValuedProduct(100.0, product,
                LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30));
        when(mockValuedProductService.createValuedProduct(any(CreateValuedProductDto.class)))
                .thenReturn(valuedProduct);
        when(mockDateTimeService.now()).thenReturn(LocalDateTime.of(2014, Month.JANUARY, 1, 10, 10, 30));

        List<ProductDelivery> productDeliveryList = new ArrayList<>();
        productDeliveryList.add(productDelivery);
        List<ValuedProduct> valuedProductList = new ArrayList<>();
        valuedProductList.add(valuedProduct);

        CheckOut checkOut = new CheckOut(productDeliveryList, valuedProductList);
        when(mockCheckOutRepository.save(any(CheckOut.class))).thenReturn(checkOut);

        valuatorService = mockValuatorService;
        deliveryService = mockDeliveryService;
        dateTimeService = mockDateTimeService;
        valuedProductService = mockValuedProductService;
        checkOutRepository = mockCheckOutRepository;
    }

    @Test
    public void test_001_WhenAUserMakesACheckoutItIsExecuted() {
        buyer.addToCart(product);

        CheckOutServiceImpl checkoutService = new CheckOutServiceImpl(
                checkOutRepository,
                mock(ProductDeliveryRepository.class),
                mock(ProductRepository.class),
                mock(ProductService.class),
                deliveryService,
                valuatorService,
                dateTimeService,
                mock(AddressService.class),
                valuedProductService
        );

        CheckOut checkOut = checkoutService.checkout(buyer);

        assertThat(checkOut).extracting(CheckOut::getProductDeliveries).asList().isNotEmpty();
        assertThat(checkOut).extracting(CheckOut::getValuedProducts).asList().isNotEmpty();

        for (ProductDelivery productDelivery : checkOut.getProductDeliveries()) {
            assertThat(productDelivery).extracting(ProductDelivery::getDeliveryCost).isEqualTo(50.0);
            assertThat(productDelivery).extracting(ProductDelivery::getTo).isEqualTo(buyerAddress);
            assertThat(productDelivery).extracting(ProductDelivery::getFrom).isEqualTo(sellerAddress);
        }

        for (ValuedProduct valuedProduct : checkOut.getValuedProducts()) {
            assertThat(valuedProduct).extracting(ValuedProduct::getValue).isEqualTo(100.0);
            assertThat(valuedProduct).extracting(ValuedProduct::getDateTime).isEqualTo(LocalDateTime.of(2014,
                    Month.JANUARY, 1, 10, 10, 30));
        }
    }

}