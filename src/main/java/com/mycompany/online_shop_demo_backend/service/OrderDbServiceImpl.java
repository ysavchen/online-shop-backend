package com.mycompany.online_shop_demo_backend.service;

import com.mycompany.online_shop_demo_backend.domain.Order;
import com.mycompany.online_shop_demo_backend.domain.OrderToBooks;
import com.mycompany.online_shop_demo_backend.repositories.OrderRepository;
import com.mycompany.online_shop_demo_backend.repositories.OrderToBooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDbServiceImpl implements OrderDbService {

    private final OrderRepository orderRepository;
    private final OrderToBooksRepository orderToBooksRepository;

    @Override
    public Order save(Order order) {
        Order savedOrder = orderRepository.save(order);
        order.getOrderToBooks().forEach(orderToBooksRepository::saveOrderToBooks);
        return savedOrder;
    }
}
