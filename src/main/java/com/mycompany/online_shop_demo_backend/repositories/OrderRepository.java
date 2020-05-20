package com.mycompany.online_shop_demo_backend.repositories;

import com.mycompany.online_shop_demo_backend.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
