package com.mycompany.online_shop_demo_backend.repositories;

import com.mycompany.online_shop_demo_backend.domain.OrderBook;
import com.mycompany.online_shop_demo_backend.domain.OrderBookId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderBookRepository extends JpaRepository<OrderBook, OrderBookId> {

    @Modifying
    @Query(value = "insert into order_book (order_id, book_id) " +
            "values (:#{#orderBook.order.id}, :#{#orderBook.book.id})", nativeQuery = true)
    void saveOrderBook(@Param("orderBook") OrderBook orderBook);
}
