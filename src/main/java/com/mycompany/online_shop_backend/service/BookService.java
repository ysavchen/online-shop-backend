package com.mycompany.online_shop_backend.service;

import com.mycompany.online_shop_backend.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> getById(long id);

    List<Book> getAllBooks();
}
