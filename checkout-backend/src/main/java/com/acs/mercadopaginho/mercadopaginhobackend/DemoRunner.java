package com.acs.mercadopaginho.mercadopaginhobackend;

import com.acs.mercadopaginho.mercadopaginhobackend.address.business.service.AddressService;
import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.AddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.address.presentation.dto.CreateAddressDto;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model.CheckOut;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.presentation.dto.CreateCheckOutDto;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.business.service.ProductDeliveryService;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.business.service.CheckOutService;
import com.acs.mercadopaginho.mercadopaginhobackend.product.business.service.ProductService;
import com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto.CreateProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.product.presentation.dto.ProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.model.ProductDelivery;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.dto.CreateProductDeliveryDto;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.business.service.SaleService;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.factory.SaleFactory;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.persistence.model.Sale;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.presentation.dto.CreateSaleDto;
import com.acs.mercadopaginho.mercadopaginhobackend.user.business.service.UserService;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.repository.UserRepository;
import com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto.CreateUserDto;
import com.acs.mercadopaginho.mercadopaginhobackend.user.presentation.dto.UserDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model.ValuedProduct;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.CreateValuedProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.ValuedProductDto;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.service.business.ValuedProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Component("DemoRunner")
@Transactional
public class DemoRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DemoRunner.class);

    private UserService userService;
    private AddressService addressService;
    private SaleService saleService;
    private CheckOutService checkOutService;
    private ProductDeliveryService productDeliveryService;
    private ProductService productService;
    private ValuedProductService valuedProductService;
    private ProductDeliveryService deliveryService;
    private final Environment env;
    private UserRepository userRepository;
    private boolean createdData = false;

    @Autowired
    public DemoRunner(UserService userService, UserRepository userRepository, AddressService addressService,
                      ProductService productService, ValuedProductService valuedProductService,
                      ProductDeliveryService productDeliveryService, CheckOutService checkOutService,
                      SaleService saleService, ProductDeliveryService deliveryService, Environment env) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.addressService = addressService;
        this.productService = productService;
        this.valuedProductService = valuedProductService;
        this.productDeliveryService = productDeliveryService;
        this.checkOutService = checkOutService;
        this.saleService = saleService;
        this.deliveryService = deliveryService;
        this.env = env;
    }

    @Override
    public void run(String... args) {
        createDemoData();
    }

    private void createDemoData() {
        if (createdData) {
            logger.warn("Creating data again?");
            return;
        }

        userRepository.deleteAll();

        logger.info("Creating demo data ...");
        User ironMan = DummyModelRepository.getIronMan();
        CreateUserDto createUserDto = new CreateUserDto(ironMan.getName(), ironMan.getSurname());
        logger.info("Creating user " + createUserDto.getName() + " " + createUserDto.getSurname());
        User ironManDto = userService.createUser(createUserDto);
        logger.info("IronMan has id " + ironManDto.getId());

        User captainAmerica = DummyModelRepository.getCaptainAmerica();
        createUserDto = new CreateUserDto(captainAmerica.getName(), captainAmerica.getSurname());
        logger.info("Creating user " + createUserDto.getName() + " " + createUserDto.getSurname());
        User captainAmericaDto = userService.createUser(createUserDto);
        logger.info("Captain America has id " + captainAmericaDto.getId());

        Address ironManAddress = DummyModelRepository.getIronManAddress();
        CreateAddressDto createAddressDto = new CreateAddressDto(ironManAddress.getHeight(),
                ironManAddress.getStreetName(),
                ironManAddress.getCityName(), ironManAddress.getProvince(),
                ironManAddress.getPostalCode());
        logger.info("Creating address " + createAddressDto.getStreetName() + ", " + createAddressDto.getCityName() +
                ", " + createAddressDto.getProvince());
        Address ironManAddressDto = addressService.createAddress(createAddressDto);

        logger.info("Adding address to user 1");
        userService.addAddress(ironManDto.getId(), ironManAddressDto.getId());

        Address captainAmericaAddress = DummyModelRepository.getCaptainAmericaAddress();
        createAddressDto = new CreateAddressDto(captainAmericaAddress.getHeight(),
                captainAmericaAddress.getStreetName(), captainAmericaAddress.getCityName(),
                captainAmericaAddress.getProvince(),
                captainAmericaAddress.getPostalCode());
        logger.info("Creating address " + createAddressDto.getStreetName() + ", " + createAddressDto.getCityName() +
                ", " + createAddressDto.getProvince());
        Address addressDto2 = addressService.createAddress(createAddressDto);

        logger.info("Adding address to user 2");
        userService.addAddress(captainAmericaDto.getId(), addressDto2.getId());

        String productName = "ARC Reactor";
        CreateProductDto arcReactorCreateProductDto = new CreateProductDto(productName, ironManAddressDto.getId(),
                ironManDto.getId());
        logger.info("Creating product " + arcReactorCreateProductDto.getProductName());
        Product arcReactorproductDto = productService.createProduct(arcReactorCreateProductDto);
        logger.info("Product created with id: " + arcReactorproductDto.getId());

        CreateValuedProductDto createArcReactorValuedProductDto = new CreateValuedProductDto(190,
                arcReactorproductDto.getId());
        logger.info("Creating valued-product with value " + createArcReactorValuedProductDto.getValue());
        ValuedProduct arcReactorValuedProductDto =
                valuedProductService.createValuedProduct(createArcReactorValuedProductDto);
        logger.info("Valued product created with id: " + arcReactorValuedProductDto.getId());

        String shield = "Vibranium-steel alloy shield";
        CreateProductDto shieldProductDto =  new CreateProductDto(shield, ironManAddressDto.getId(), ironManDto.getId());
        logger.info("Creating product " + shieldProductDto.getProductName());
        Product vibraniumShield = productService.createProduct(shieldProductDto);
        logger.info("Product created with id: " + vibraniumShield.getId());

        CreateValuedProductDto createValuedShield = new CreateValuedProductDto(230,
                vibraniumShield.getId());
        logger.info("Creating valued-product with value " + createValuedShield.getValue());
        ValuedProduct valuedShield =
                valuedProductService.createValuedProduct(createValuedShield);
        logger.info("Valued product created with id: " + valuedShield.getId());

        CreateProductDeliveryDto deliveryDto = new CreateProductDeliveryDto(30, addressDto2.getId(), ironManAddressDto.getId(), vibraniumShield.getId());
        ProductDelivery delivery = deliveryService.createProductDelivery(deliveryDto);
        logger.info("Created delivery: " + delivery.getId());

        List<Long> productDeliveryIdList = new ArrayList<>();
        productDeliveryIdList.add(delivery.getId());
        List<Long> productIdList = new ArrayList<>();
        productIdList.add(vibraniumShield.getId());

        CreateCheckOutDto checkOutDto = new CreateCheckOutDto(productDeliveryIdList, productIdList);
        CheckOut checkOut = checkOutService.createCheckOut(checkOutDto);
        logger.info("Created checkout: " + checkOut.getId());

        CreateSaleDto saleDto = new CreateSaleDto(captainAmericaDto.getId(), checkOut.getId());
        Sale sale = saleService.create(saleDto);
        logger.info("Created sale: " + sale.getId());


        createUserAndAddress(10);
        createdData = true;
    }

    private void createUserAndAddress(int i) {
        for (int j = 0; j < i; j++) {
            User user = DummyModelRepository.getUser();
            CreateUserDto createUserDto = new CreateUserDto(user.getName(), user.getSurname());
            logger.info("Creating user " + createUserDto.getName() + " " + createUserDto.getSurname());
            User userDto = userService.createUser(createUserDto);
            logger.info("User has id " + userDto.getId());

            Address userAddress = DummyModelRepository.getAddress();
            CreateAddressDto createAddressDto = new CreateAddressDto(userAddress.getHeight(),
                    userAddress.getStreetName(), userAddress.getCityName(),
                    userAddress.getProvince(),
                    userAddress.getPostalCode());
            logger.info("Creating address " + createAddressDto.getStreetName() + ", " + createAddressDto.getCityName() +
                    ", " + createAddressDto.getProvince());
            Address addressDto = addressService.createAddress(createAddressDto);

            logger.info("Adding address to user " + userDto.getId());
            userService.addAddress(userDto.getId(), addressDto.getId());

            String productName = "Product";
            CreateProductDto createProductDto = new CreateProductDto(productName, addressDto.getId(),
                    userDto.getId());
            logger.info("Creating product " + createProductDto.getProductName());
            Product arcReactorproductDto = productService.createProduct(createProductDto);
            logger.info("Product created with id: " + arcReactorproductDto.getId());
        }
    }
}

