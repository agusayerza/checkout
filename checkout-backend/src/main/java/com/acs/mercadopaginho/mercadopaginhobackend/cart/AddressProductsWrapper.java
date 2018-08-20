package com.acs.mercadopaginho.mercadopaginhobackend.cart;

import com.acs.mercadopaginho.mercadopaginhobackend.common.IdDto;

import java.util.List;

public class AddressProductsWrapper {

    private List<IdDto> products;
    private IdDto address;

    public List<IdDto> getProducts() {
        return products;
    }

    public IdDto getAddress() {
        return address;
    }
}
