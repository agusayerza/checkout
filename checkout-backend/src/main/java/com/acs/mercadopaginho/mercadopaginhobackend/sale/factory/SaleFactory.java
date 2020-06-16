package com.acs.mercadopaginho.mercadopaginhobackend.sale.factory;

import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model.CheckOut;
import com.acs.mercadopaginho.mercadopaginhobackend.sale.persistence.model.Sale;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;

public class SaleFactory {
    public static Sale create(User user, CheckOut checkOut) {
        return new Sale(user, checkOut);
    }
}
