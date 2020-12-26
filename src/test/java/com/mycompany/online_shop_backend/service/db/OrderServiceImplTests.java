package com.mycompany.online_shop_backend.service.db;

import com.mycompany.online_shop_backend.domain.*;
import com.mycompany.online_shop_backend.exceptions.EntityNotFoundException;
import com.mycompany.online_shop_backend.repositories.BookRepository;
import com.mycompany.online_shop_backend.repositories.OrderBookRepository;
import com.mycompany.online_shop_backend.repositories.OrderRepository;
import com.mycompany.online_shop_backend.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Import(OrderServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class OrderServiceImplTests {

    private final Author author = new Author(1, "Author One");
    private final Book book = new Book(1, "Book One", "Description One", author, "/imageOne", 22.95);
    private final Order order = new Order(
            1L,
            "Name One Surname One",
            new Address(1L, "Address, 1"),
            new Phone(1L, "+1111 1111"),
            "userOne@test.com",
            ZonedDateTime.now().toEpochSecond(),
            new HashSet<>()
    );
    private final OrderBook orderBook = new OrderBook(order, book);

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderBookRepository orderBookRepository;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private OrderServiceImpl orderService;

    @BeforeEach
    void setup() {
        order.setOrderBooks(Set.of(orderBook));
    }

    @Test
    public void saveOrder() {
        when(orderRepository.save(order)).thenReturn(order);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        doNothing().when(orderBookRepository).saveOrderBook(orderBook);

        orderService.save(order);
        verify(orderRepository, times(1)).save(order);
        verify(orderBookRepository, times(1)).saveOrderBook(orderBook);
    }

    @Test
    public void saveOrderWithNonExistingBook() {
        when(orderRepository.save(order)).thenReturn(order);
        when(bookRepository.findById(book.getId())).thenThrow(new EntityNotFoundException("not found"));

        assertThrows(EntityNotFoundException.class, () -> orderService.save(order));
        verify(orderRepository, times(1)).save(order);
        verify(orderBookRepository, never()).saveOrderBook(orderBook);
    }
}
