package com.acs.mercadopaginho.mercadopaginhobackend.valuator;

import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;

public interface ValuatorService {
    double value(Product product);
}
