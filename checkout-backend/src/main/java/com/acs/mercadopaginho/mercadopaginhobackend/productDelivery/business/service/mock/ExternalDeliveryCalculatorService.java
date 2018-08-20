package com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.business.service.mock;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;
import com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.business.service.ExternalDeliveryCalculator;
import org.springframework.stereotype.Service;

@Service
public class ExternalDeliveryCalculatorService implements ExternalDeliveryCalculator {
    @Override
    public double calculateCost(Address to, Address from) {
        return 40;
    }
}
