package com.mycompany.online_shop_demo_backend.dto.response;

import com.mycompany.online_shop_demo_backend.domain.Order;
import com.mycompany.online_shop_demo_backend.domain.OrderToBook;
import com.mycompany.online_shop_demo_backend.dto.BookDto;
import com.mycompany.online_shop_demo_backend.dto.DeliveryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private long id;
    private DeliveryDto delivery;
    private List<BookDto> books;

    public static OrderResponse toDto(Order order) {
        return new OrderResponse(
                order.getId(),
                new DeliveryDto(
                        order.getAddresseeName(),
                        order.getAddress().getValue(),
                        order.getAddress().getZipCode(),
                        order.getPhone().getValue(),
                        order.getEmail()
                ),
                order.getOrderToBooks().stream()
                        .map(OrderToBook::getBook)
                        .map(BookDto::toDto)
                        .collect(toList())
        );
    }
}
