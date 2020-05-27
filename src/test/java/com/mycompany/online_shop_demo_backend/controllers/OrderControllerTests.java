package com.mycompany.online_shop_demo_backend.controllers;

import com.google.gson.Gson;
import com.mycompany.online_shop_demo_backend.domain.*;
import com.mycompany.online_shop_demo_backend.dto.BookDto;
import com.mycompany.online_shop_demo_backend.dto.request.OrderRequest;
import com.mycompany.online_shop_demo_backend.dto.response.OrderResponse;
import com.mycompany.online_shop_demo_backend.security.SecurityConfiguration;
import com.mycompany.online_shop_demo_backend.security.TokenAuthenticationFilter;
import com.mycompany.online_shop_demo_backend.security.TokenProperties;
import com.mycompany.online_shop_demo_backend.security.UserDetailsServiceImpl;
import com.mycompany.online_shop_demo_backend.service.db.OrderDbService;
import com.mycompany.online_shop_demo_backend.service.db.UserDbService;
import com.mycompany.online_shop_demo_backend.service.security.SecurityService;
import com.mycompany.online_shop_demo_backend.service.security.TokenServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@Import({TokenAuthenticationFilter.class,
        TokenProperties.class,
        TokenServiceImpl.class,
        SecurityConfiguration.class,
        UserDetailsServiceImpl.class})
public class OrderControllerTests {
    private final User userOne = new User(1, "Name One", "Surname One", "userOne@test.com", "Encoded Start01#");
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
            new HashSet<>()
    );
    private final Set<OrderBook> orderBooks = Set.of(new OrderBook(order, bookOne), new OrderBook(order, bookTwo));

    private final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderDbService orderDbService;

    @MockBean
    private UserDbService userDbService;

    @MockBean
    private SecurityService securityService;

    @BeforeEach
    void setup() {
        order.setOrderBooks(orderBooks);
    }

    @Test
    public void createOrder() throws Exception {
        when(orderDbService.save(any(Order.class))).thenReturn(order);

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
        when(orderDbService.getOrdersByEmail(userOne.getEmail())).thenReturn(List.of(order));

        mockMvc.perform(
                get("/api/users/{id}/orders", userOne.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(gson.toJson(List.of(orderResponse))));
    }
}
