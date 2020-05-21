package com.mycompany.online_shop_demo_backend.dto;

import com.mycompany.online_shop_demo_backend.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private long id;
    private DeliveryDto delivery;
    private List<BookDto> books;

    public static OrderDto toDto(Order order) {
        return new OrderDto(
                order.getId(),
                new DeliveryDto(
                        order.getAddresseeName(),
                        order.getAddress().getValue(),
                        order.getAddress().getZipCode(),
                        order.getPhone().getValue(),
                        order.getEmail().getValue()
                ),
                order.getOrderToBooks().stream()
                        .map(OrderToBook::getBook)
                        .map(BookDto::toDto)
                        .collect(toList())
        );
    }

    public static Order toDomainObject(OrderDto dto) {
        final Order order = new Order();
        order.setId(dto.id)
                .setAddresseeName(dto.delivery.getName())
                .setAddress(new Address(0, dto.delivery.getAddress(), dto.delivery.getZipcode()))
                .setPhone(new Phone(0, dto.delivery.getPhone()))
                .setEmail(new Email(0, dto.delivery.getEmail()));

        dto.books.stream()
                .map(BookDto::toDomainObject)
                .forEach(order::addBook);

        return order;
    }
}
