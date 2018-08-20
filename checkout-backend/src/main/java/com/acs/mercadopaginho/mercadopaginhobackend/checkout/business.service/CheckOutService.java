package com.acs.mercadopaginho.mercadopaginhobackend.checkout.business.service;

import com.acs.mercadopaginho.mercadopaginhobackend.checkout.persistence.model.CheckOut;
import com.acs.mercadopaginho.mercadopaginhobackend.checkout.presentation.dto.CreateCheckOutDto;
import com.acs.mercadopaginho.mercadopaginhobackend.common.IdDto;
import com.acs.mercadopaginho.mercadopaginhobackend.user.persistence.model.User;

import java.util.List;

public interface CheckOutService {

    CheckOut getCheckOut(Long id);

    List<CheckOut> getAll();

    CheckOut createCheckOut(CreateCheckOutDto createCheckOutDto);

    CheckOut createCheckOut(List<Long> productDeliveryIdList, List<Long> valuedProductIdList);

    CheckOut createCheckOut(List<IdDto> products, IdDto address);

    CheckOut checkout(User buyer);
}
