package com.mycompany.online_shop_demo_backend.controllers.rest;

import com.mycompany.online_shop_demo_backend.domain.Order;
import com.mycompany.online_shop_demo_backend.dto.OrderDto;
import com.mycompany.online_shop_demo_backend.service.OrderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderDbService dbService;

    @PostMapping("/api/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        Order order = OrderDto.toDomainObject(orderDto);
        return OrderDto.toDto(dbService.save(order));
    }
}
