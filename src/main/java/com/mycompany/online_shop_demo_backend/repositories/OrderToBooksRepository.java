package com.mycompany.online_shop_demo_backend.repositories;

import com.mycompany.online_shop_demo_backend.domain.OrderToBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderToBooksRepository extends JpaRepository<OrderToBooks, Long> {

    @Modifying
    @Query(value = "insert into order_to_books (id, order_id, book_id) " +
            "values (null, :#{#orderToBooks.order.id}, :#{#orderToBooks.book.id})", nativeQuery = true)
    void saveOrderToBooks(@Param("orderToBooks") OrderToBooks orderToBooks);
}
