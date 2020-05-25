package com.mycompany.online_shop_demo_backend.service.db;

import com.mycompany.online_shop_demo_backend.repositories.BookRepository;
import com.mycompany.online_shop_demo_backend.repositories.OrderBookRepository;
import com.mycompany.online_shop_demo_backend.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Import(OrderDbServiceImpl.class)
@ExtendWith(SpringExtension.class)
public class OrderDbServiceImplTests {

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderBookRepository orderBookRepository;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private OrderDbServiceImpl orderDbService;

    @Test
    public void saveOrder() {

    }

    @Test
    public void saveOrderWithNonExistingBook() {

    }
}
