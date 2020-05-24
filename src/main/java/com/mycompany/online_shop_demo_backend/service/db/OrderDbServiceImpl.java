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
@Transactional
@RequiredArgsConstructor
public class OrderDbServiceImpl implements OrderDbService {

    private final OrderRepository orderRepository;
    private final OrderBookRepository orderBookRepository;
    private final BookRepository bookRepository;

    @Override
    public Order save(Order order) {
        order.getOrderBooks().forEach(orderToBook -> {
            long bookId = orderToBook.getBook().getId();
            bookRepository.findById(bookId).ifPresentOrElse(
                    book -> orderBookRepository.saveOrderBook(orderToBook),
                    () -> {
                        String message = "Book (id = " + bookId + ") is not found. " +
                                "Order for " + order.getEmail() + " is not saved.";
                        logger.error(message);
                        throw new EntityNotFoundException(message);
                    }
            );
        });
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Order> getOrdersByEmail(String email) {
        return orderRepository.findByEmail(email);
    }
}
