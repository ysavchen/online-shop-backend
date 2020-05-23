package com.mycompany.online_shop_demo_backend.service.db;

import com.mycompany.online_shop_demo_backend.domain.Order;
import com.mycompany.online_shop_demo_backend.repositories.OrderRepository;
import com.mycompany.online_shop_demo_backend.repositories.OrderToBooksRepository;
import com.mycompany.online_shop_demo_backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDbServiceImpl implements OrderDbService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderToBooksRepository orderToBooksRepository;

    @Override
    public Order save(Order order) {
        Order savedOrder = orderRepository.save(order);
        order.getOrderToBooks().forEach(orderToBooksRepository::saveOrderToBooks);
        return savedOrder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findByEmail(email);
    }
}
