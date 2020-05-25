package com.mycompany.online_shop_demo_backend.service.db;

import com.mycompany.online_shop_demo_backend.domain.*;
import com.mycompany.online_shop_demo_backend.exceptions.EntityNotFoundException;
import com.mycompany.online_shop_demo_backend.repositories.BookRepository;
import com.mycompany.online_shop_demo_backend.repositories.OrderBookRepository;
import com.mycompany.online_shop_demo_backend.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@Import(OrderDbServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class OrderDbServiceImplTests {

    private final Author author = new Author(1, "Author One");
    private final Book book = new Book(1, "Book One", "Description One", author, "/imageOne", 22.95);
    private final Order order = new Order(
            1L,
            "Name One Surname One",
            new Address(1L, "Address, 1"),
            new Phone(1L, "+1111 1111"),
            "userOne@test.com",
            LocalDateTime.parse("2020-05-25T10:35:56.879695500"),
            new ArrayList<>()
    );
    private final OrderBook orderBook = new OrderBook(order, book);

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderBookRepository orderBookRepository;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private OrderDbServiceImpl orderDbService;

    @BeforeEach
    void setup() {
        order.setOrderBooks(List.of(orderBook));
    }

    @Test
    public void saveOrder() {
        when(orderRepository.save(order)).thenReturn(order);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        doNothing().when(orderBookRepository).saveOrderBook(orderBook);

        orderDbService.save(order);
        verify(orderRepository, times(1)).save(order);
        verify(orderBookRepository, times(1)).saveOrderBook(orderBook);
    }

    @Test
    public void saveOrderWithNonExistingBook() {
        when(orderRepository.save(order)).thenReturn(order);
        when(bookRepository.findById(book.getId())).thenThrow(new EntityNotFoundException("not found"));

        assertThrows(EntityNotFoundException.class, () -> orderDbService.save(order));
        verify(orderRepository, times(1)).save(order);
        verify(orderBookRepository, never()).saveOrderBook(orderBook);
    }
}
