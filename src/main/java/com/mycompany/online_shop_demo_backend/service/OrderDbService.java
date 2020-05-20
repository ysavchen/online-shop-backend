package com.mycompany.online_shop_demo_backend.service;

import com.mycompany.online_shop_demo_backend.domain.Order;

public interface OrderDbService {

    Order save(Order order);
}
