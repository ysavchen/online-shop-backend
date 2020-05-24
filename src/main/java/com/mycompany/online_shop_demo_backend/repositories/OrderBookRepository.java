package com.mycompany.online_shop_demo_backend.repositories;

import com.mycompany.online_shop_demo_backend.domain.OrderBook;
import com.mycompany.online_shop_demo_backend.domain.OrderBookId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBookRepository extends JpaRepository<OrderBook, OrderBookId> {
}
