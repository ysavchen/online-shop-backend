package com.mycompany.online_shop_backend.controllers;

import com.mycompany.online_shop_backend.domain.Order;
import com.mycompany.online_shop_backend.dto.request.OrderRequest;
import com.mycompany.online_shop_backend.dto.response.OrderResponse;
import com.mycompany.online_shop_backend.service.OrderService;
import com.mycompany.online_shop_backend.service.security.SecurityService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final SecurityService securityService;

    @ApiOperation("Creates an order")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Successful execution"),
            @ApiResponse(code = 401, message = "Invalid authentication"),
            @ApiResponse(code = 500, message = "Error during execution")
    })
    @PostMapping(path = "/api/orders",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        Order order = OrderRequest.toDomainObject(request);
        return OrderResponse.toDto(orderService.save(order));
    }

    @ApiOperation("Gets orders for an authenticated user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Successful execution"),
            @ApiResponse(code = 401, message = "Invalid authentication"),
            @ApiResponse(code = 500, message = "Error during execution")
    })
    @GetMapping(path = "/api/users/{id}/orders",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrderResponse> getUserOrders(HttpServletRequest request) {
        String email = securityService.getUsernameFromRequest(request);
        return orderService.getOrdersByEmail(email)
                .stream()
                .map(OrderResponse::toDto)
                .collect(toList());
    }
}
