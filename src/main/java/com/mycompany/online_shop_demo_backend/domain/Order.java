package com.mycompany.online_shop_demo_backend.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Set<OrderToBook> orderToBooks = new HashSet<>();

    public void addBook(Book book) {
        OrderToBook orderToBook = new OrderToBook(this, book);
        orderToBooks.add(orderToBook);
    }

    public void removeBook(Book book) {
        for (Iterator<OrderToBook> iterator = orderToBooks.iterator();
             iterator.hasNext(); ) {
            OrderToBook orderToBook = iterator.next();
            if (orderToBook.getOrder().equals(this) && orderToBook.getBook().equals(book)) {
                iterator.remove();
                orderToBook.setOrder(null);
                orderToBook.setBook(null);
            }
        }
    }
}
