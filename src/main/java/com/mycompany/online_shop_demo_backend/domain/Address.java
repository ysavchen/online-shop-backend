package com.mycompany.online_shop_demo_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "address", nullable = false)
    private String value;

    @Column(name = "zip_code")
    private String zipCode;

}
