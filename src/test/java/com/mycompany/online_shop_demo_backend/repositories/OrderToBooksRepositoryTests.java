package com.mycompany.online_shop_demo_backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class OrderToBooksRepositoryTests {

    @Autowired
    private OrderToBooksRepository repository;

}
