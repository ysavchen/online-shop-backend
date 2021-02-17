package com.mycompany.online_shop_backend.dto.request;

import com.mycompany.online_shop_backend.domain.Address;
import com.mycompany.online_shop_backend.domain.Order;
import com.mycompany.online_shop_backend.domain.Phone;
import com.mycompany.online_shop_backend.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private String name;
    private String address;
    private String phone;
    private String email;
    private List<BookDto> books;

    public static Order toEntity(OrderRequest dto) {
        Order order = new Order();
        order.setId(0L)
                .setAddresseeName(dto.getName())
                .setAddress(new Address(0L, dto.getAddress()))
                .setPhone(new Phone(0L, dto.getPhone()))
                .setEmail(dto.getEmail());

        dto.books.stream()
                .map(BookDto::toEntity)
                .forEach(order::addBook);

        return order;
    }
}
