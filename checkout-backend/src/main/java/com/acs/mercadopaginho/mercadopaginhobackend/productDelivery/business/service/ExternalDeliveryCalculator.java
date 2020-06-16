package com.acs.mercadopaginho.mercadopaginhobackend.productDelivery.business.service;

import com.acs.mercadopaginho.mercadopaginhobackend.address.persistence.model.Address;

public interface ExternalDeliveryCalculator {
    double calculateCost(Address to, Address from);
}