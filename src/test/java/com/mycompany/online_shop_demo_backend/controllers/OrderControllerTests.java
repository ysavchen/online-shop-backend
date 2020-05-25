package com.mycompany.online_shop_demo_backend.controllers;

import com.google.gson.Gson;
import com.mycompany.online_shop_demo_backend.domain.*;
import com.mycompany.online_shop_demo_backend.dto.BookDto;
import com.mycompany.online_shop_demo_backend.dto.request.OrderRequest;
import com.mycompany.online_shop_demo_backend.dto.response.OrderResponse;
import com.mycompany.online_shop_demo_backend.service.db.OrderDbService;
import com.mycompany.online_shop_demo_backend.service.security.SecurityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTests {
    private final User userOne = new User(1, "Name One", "Surname One", "userOne@test.com", "$2a$10$Wfn//hRL3NrA9DG0fYRtYuhzLZc8CLDNKvv4Twcx55XEDsWABlD8K");
    private final Author authorOne = new Author(1, "Author One");
    private final Author authorTwo = new Author(2, "Author Two");

    private final Book bookOne = new Book(1, "Book One", "Description One", authorOne, "/imageOne", 22.95);
    private final Book bookTwo = new Book(2, "Book Two", "Description Two", authorTwo, "/imageTwo", 46.00);
    private final BookDto bookOneDto = BookDto.toDto(bookOne);
    private final BookDto bookTwoDto = BookDto.toDto(bookTwo);
    private final List<BookDto> bookDtos = List.of(bookOneDto, bookTwoDto);

    private final OrderRequest orderRequest = new OrderRequest(
            0L,
            userOne.getFirstName() + " " + userOne.getLastName(),
            "Address, 1",
            "+1111 1111",
            userOne.getEmail(),
            bookDtos
    );
    private final OrderResponse orderResponse = new OrderResponse(
            1L,
            userOne.getFirstName() + " " + userOne.getLastName(),
            "Address, 1",
            "+1111 1111",
            userOne.getEmail(),
            "2020-05-25T10:35:56.879695500",
            bookDtos
    );

    private final Order order = new Order(
            1L,
            userOne.getFirstName() + " " + userOne.getLastName(),
            new Address(1L, "Address, 1"),
            new Phone(1L, "+1111 1111"),
            userOne.getEmail(),
            LocalDateTime.parse("2020-05-25T10:35:56.879695500"),
            new ArrayList<>()
    );
    private final List<OrderBook> orderBooks = List.of(new OrderBook(order, bookOne), new OrderBook(order, bookTwo));

    private final Gson gson = new Gson();

    @BeforeEach
    void setup() {
        order.setOrderBooks(orderBooks);

    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderDbService dbService;

    @MockBean
    private SecurityService securityService;

    @Test
    public void createOrder() throws Exception {
        when(dbService.save(any(Order.class))).thenReturn(order);
        mockMvc.perform(
                post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(orderRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(gson.toJson(orderResponse)));
    }

    @WithMockUser(username = "userOne@test.com")
    @Test
    public void getUserOrders() throws Exception {
        when(securityService.getUsernameFromRequest(any(HttpServletRequest.class))).thenReturn(userOne.getEmail());
        when(dbService.getOrdersByEmail(userOne.getEmail())).thenReturn(List.of(order));
        mockMvc.perform(
                get("/api/users/{id}/orders", userOne.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(gson.toJson(List.of(orderResponse))));
    }
}
