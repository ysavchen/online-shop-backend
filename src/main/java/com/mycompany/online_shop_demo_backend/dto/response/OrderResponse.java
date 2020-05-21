package com.mycompany.online_shop_demo_backend.dto.response;

import com.mycompany.online_shop_demo_backend.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private long id;

    public static OrderResponse toDto(Order order) {
        return new OrderResponse(order.getId());
    }
}
