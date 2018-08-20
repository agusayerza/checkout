package com.acs.mercadopaginho.mercadopaginhobackend.valuator;

import com.acs.mercadopaginho.mercadopaginhobackend.product.persistence.model.Product;
import org.springframework.stereotype.Service;


@Service

public class ValuatorServiceImpl implements ValuatorService {
    @Override
    public double value(Product product) {
        return 100;
    }
}
