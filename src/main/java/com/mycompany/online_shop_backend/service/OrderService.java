package com.mycompany.online_shop_backend.service;

import com.mycompany.online_shop_backend.domain.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order);

    List<Order> getOrdersByEmail(String email);
}
