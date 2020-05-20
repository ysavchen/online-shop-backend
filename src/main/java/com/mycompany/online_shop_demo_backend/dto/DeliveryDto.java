package com.mycompany.online_shop_demo_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class DeliveryDto {

    private String name;
    private String address;
    private String zipcode;
    private String phone;
    private String email;

}
