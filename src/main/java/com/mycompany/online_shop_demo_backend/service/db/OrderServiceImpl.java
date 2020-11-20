package com.mycompany.online_shop_demo_backend.service.db;

import com.mycompany.online_shop_demo_backend.domain.Order;
import com.mycompany.online_shop_demo_backend.exceptions.EntityNotFoundException;
import com.mycompany.online_shop_demo_backend.repositories.BookRepository;
import com.mycompany.online_shop_demo_backend.repositories.OrderBookRepository;
import com.mycompany.online_shop_demo_backend.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderBookRepository orderBookRepository;
    private final BookRepository bookRepository;

    @Transactional
    @Override
    public Order save(Order order) {
        Order savedOrder = orderRepository.save(order);
        order.getOrderBooks().forEach(orderBook -> {
            orderBook.setOrder(order);
            long bookId = orderBook.getBook().getId();
            bookRepository.findById(bookId).ifPresentOrElse(
                    book -> orderBookRepository.saveOrderBook(orderBook),
                    () -> {
                        String message = "Book (id = " + bookId + ") is not found. " +
                                "Order for " + order.getEmail() + " is not saved.";
                        logger.error(message);
                        throw new EntityNotFoundException(message);
                    }
            );
        });
        return savedOrder;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findByEmail(email);
    }
}
