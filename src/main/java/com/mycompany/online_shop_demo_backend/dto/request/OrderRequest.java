package com.mycompany.online_shop_demo_backend.dto.request;

import com.mycompany.online_shop_demo_backend.domain.Address;
import com.mycompany.online_shop_demo_backend.domain.Order;
import com.mycompany.online_shop_demo_backend.domain.Phone;
import com.mycompany.online_shop_demo_backend.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private List<BookDto> books;

    public static Order toDomainObject(OrderRequest dto) {
        Order order = new Order();
        order.setId(dto.id)
                .setAddresseeName(dto.getName())
                .setAddress(new Address(0, dto.getAddress()))
                .setPhone(new Phone(0, dto.getPhone()))
                .setEmail(dto.getEmail());

        dto.books.stream()
                .map(BookDto::toDomainObject)
                .forEach(order::addBook);

        return order;
    }
}
