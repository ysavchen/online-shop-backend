package com.mycompany.online_shop_demo_backend.dto.request;

import com.mycompany.online_shop_demo_backend.domain.Address;
import com.mycompany.online_shop_demo_backend.domain.Email;
import com.mycompany.online_shop_demo_backend.domain.Order;
import com.mycompany.online_shop_demo_backend.domain.Phone;
import com.mycompany.online_shop_demo_backend.dto.BookDto;
import com.mycompany.online_shop_demo_backend.dto.DeliveryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private long id;
    private DeliveryDto delivery;
    private List<BookDto> books;

    public static Order toDomainObject(OrderRequest dto) {
        Order order = new Order();
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
