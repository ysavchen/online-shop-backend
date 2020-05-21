package com.mycompany.online_shop_demo_backend.controllers.rest;

import com.mycompany.online_shop_demo_backend.domain.Order;
import com.mycompany.online_shop_demo_backend.dto.request.OrderRequest;
import com.mycompany.online_shop_demo_backend.dto.request.UserOrdersRequest;
import com.mycompany.online_shop_demo_backend.dto.response.OrderResponse;
import com.mycompany.online_shop_demo_backend.service.db.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderDbService dbService;

    @PostMapping(path = "/api/orders",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        Order order = OrderRequest.toDomainObject(request);
        return OrderResponse.toDto(dbService.save(order));
    }

    @GetMapping(path = "/api/users/{id}/orders",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getUserOrders(@RequestBody UserOrdersRequest request) {
        return null;
    }
}
