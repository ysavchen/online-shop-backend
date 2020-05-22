package com.mycompany.online_shop_demo_backend.service.db;

import com.mycompany.online_shop_demo_backend.domain.Order;
import com.mycompany.online_shop_demo_backend.domain.User;
import com.mycompany.online_shop_demo_backend.exceptions.EntityNotFoundException;
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

    public List<Order> getOrdersByUserId(long id) {
        System.out.println("DB request!!!");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User (id = " + id + ") is not found"));
        return orderRepository.findByEmail(user.getEmail());
    }
}
