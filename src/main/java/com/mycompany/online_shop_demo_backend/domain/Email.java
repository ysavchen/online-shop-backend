package com.mycompany.online_shop_demo_backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Entity
@Table(name = "emails")
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "email", nullable = false)
    private String value;

}
