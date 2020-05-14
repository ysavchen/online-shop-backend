package com.mycompany.online_shop_demo_backend.service;

import com.mycompany.online_shop_demo_backend.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDbService {

    Optional<Book> getById(long id);

    List<Book> getAllBooks();
}
