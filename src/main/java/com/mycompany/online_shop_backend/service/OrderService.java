package com.mycompany.online_shop_backend.service;

import com.mycompany.online_shop_backend.domain.Order;
import com.mycompany.online_shop_backend.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse save(Order order);

    List<OrderResponse> getOrdersByEmail(String email);
}
