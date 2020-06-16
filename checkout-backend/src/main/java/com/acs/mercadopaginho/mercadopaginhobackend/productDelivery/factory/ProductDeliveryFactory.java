package com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.factory;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.model.ProductDelivery;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.dto.ProductDeliveryDto;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.presentation.dto.CreateProductDeliveryDto;

import java.util.ArrayList;
import java.util.List;

public class ProductDeliveryFactory {
    public static ProductDelivery create(CreateProductDeliveryDto productDeliveryDto, Address to, Address from, Product product){
        return new ProductDelivery(productDeliveryDto.getDeliveryCost(), to, from, product);
    }

    public static List<ProductDeliveryDto> getProductDeliveryDtoList(List<ProductDelivery> productDeliveries) {
        ArrayList<ProductDeliveryDto> productDeliveryDtoList = new ArrayList<>();
        productDeliveries.forEach(productDelivery ->
                productDeliveryDtoList.add(new ProductDeliveryDto(productDelivery)));
        return productDeliveryDtoList;
    }
}
