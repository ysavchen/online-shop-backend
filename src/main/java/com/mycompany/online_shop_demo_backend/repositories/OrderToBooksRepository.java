package com.mycompany.online_shop_demo_backend.repositories;

import com.mycompany.online_shop_demo_backend.domain.OrderToBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderToBooksRepository extends JpaRepository<OrderToBook, Long> {

    @Modifying
    @Query(value = "insert into order_to_books (order_id, book_id) " +
            "values (:#{#orderToBooks.order.id}, :#{#orderToBooks.book.id})", nativeQuery = true)
    void saveOrderToBooks(@Param("orderToBooks") OrderToBook orderToBook);
}
