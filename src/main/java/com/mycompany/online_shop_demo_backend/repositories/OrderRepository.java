package com.mycompany.online_shop_demo_backend.repositories;

import com.mycompany.online_shop_demo_backend.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select o.* " +
            "from orders o " +
            "join users u on o.email = u.email " +
            "where u.id = :id", nativeQuery = true)
    List<Order> getOrdersByUserId(@Param("id") long id);
}
