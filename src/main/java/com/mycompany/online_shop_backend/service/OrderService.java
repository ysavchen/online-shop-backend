package com.mycompany.online_shop_backend.service;

import com.mycompany.online_shop_backend.domain.Order;
import com.mycompany.online_shop_backend.dto.request.OrderRequest;
import com.mycompany.online_shop_backend.dto.response.OrderResponse;
import com.mycompany.online_shop_backend.exceptions.EntityNotFoundException;
import com.mycompany.online_shop_backend.repositories.BookRepository;
import com.mycompany.online_shop_backend.repositories.OrderBookRepository;
import com.mycompany.online_shop_backend.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderBookRepository orderBookRepository;
    private final BookRepository bookRepository;

    @Transactional
    public OrderResponse save(OrderRequest request) {
        Order order = OrderRequest.toEntity(request);
        Order saved = orderRepository.save(order);

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

        return OrderResponse.toDto(saved);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByEmail(String email) {
        return orderRepository.findByEmail(email)
                .stream()
                .map(OrderResponse::toDto)
                .collect(toList());
    }
}
