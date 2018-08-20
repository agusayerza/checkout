package com.acs.mercadopaginho.mercadopaginhobackend.cart.service;

import com.acs.mercadopaginho.mercadopaginhobackend.cart.persistence.model.Cart;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {
    Map<Long, Cart> cartMap = new HashMap<>();

    public Cart getCartForUser(Long userId) {
        if(!cartMap.containsKey(userId)) {
            Cart cart = new Cart();
            cartMap.put(userId, cart);

        }
        return cartMap.get(userId);
    }
}
