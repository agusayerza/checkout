package com.acs.mercadopaginho.mercadopaginhobackend.checkout.factory;

import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model.CheckOut;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.persistence.model.ProductDelivery;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.persistence.model.ValuedProduct;
import com.acs.mercadopaginho.mercadopaginhobackend.valuedProduct.presentation.dto.ValuedProductDto;

import java.util.ArrayList;
import java.util.List;

public class CheckOutFactory {
    public static CheckOut create(List<ProductDelivery> productDeliveries, List<ValuedProduct> valuedProducts) {
        return new CheckOut(productDeliveries, valuedProducts);
    }

    public static List<ValuedProductDto> getCheckOutDtoList(List<ValuedProduct> valuedProducts) {
        ArrayList<ValuedProductDto> valuedProductDtoList = new ArrayList<>();
        valuedProducts.forEach(valuedProduct ->
                valuedProductDtoList.add(new ValuedProductDto(valuedProduct)));
        return valuedProductDtoList;
    }
}
