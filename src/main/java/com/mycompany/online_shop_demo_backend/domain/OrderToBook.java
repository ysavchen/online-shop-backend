package com.mycompany.online_shop_demo_backend.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_to_books")
public class OrderToBook {

    @EmbeddedId
    private OrderToBookId orderToBookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("book_id")
    private Book book;

    //todo: quantity? (in case of two same books are ordered)

    private OrderToBook() {
    }

    public OrderToBook(Order order, Book book) {
        this.order = order;
        this.book = book;
        this.orderToBookId = new OrderToBookId(order.getId(), book.getId());
    }
}
