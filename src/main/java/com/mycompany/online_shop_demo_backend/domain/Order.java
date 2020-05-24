package com.mycompany.online_shop_demo_backend.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "addressee_name", nullable = false)
    private String addresseeName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "address_id")
    private Address address;

    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "phone_id")
    private Phone phone;

    @Column(name = "email")
    private String email;

    @Column(name = "order_date_time")
    private LocalDateTime dateTime;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<OrderBook> orderBooks = new ArrayList<>(); //can be duplicate(same) books in order

    public void addBook(Book book) {
        OrderBook orderBook = new OrderBook(this, book);
        orderBooks.add(orderBook);
    }

    public void removeBook(Book book) {
        for (Iterator<OrderBook> iterator = orderBooks.iterator();
             iterator.hasNext(); ) {
            OrderBook orderBook = iterator.next();
            if (orderBook.getOrder().equals(this) && orderBook.getBook().equals(book)) {
                iterator.remove();
                orderBook.setOrder(null);
                orderBook.setBook(null);
            }
        }
    }
}
