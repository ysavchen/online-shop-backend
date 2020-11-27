package com.mycompany.online_shop_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "phones")
@NoArgsConstructor
@AllArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "phone", nullable = false)
    private String value;

}
