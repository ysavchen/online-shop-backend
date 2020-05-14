package com.mycompany.online_shop_demo_backend.repositories;

import com.mycompany.online_shop_demo_backend.domain.Author;
import com.mycompany.online_shop_demo_backend.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryImplTests {

    private final Author authorOne = new Author(1, "Author One");
    private final Author authorTwo = new Author(2, "Author Two");

    private final Book bookOne = new Book(
            1, "Book One", "Description One",
            authorOne, "/imageOne", 22.95);
    private final Book bookTwo = new Book(
            2, "Book Two", "Description Two",
            authorTwo, "/imageTwo", 46.00);

    @Autowired
    private BookRepository bookRepository;

    @Test
    void findBookById() {
        assertThat(bookRepository.findById(bookOne.getId())).get()
                .hasFieldOrPropertyWithValue("id", bookOne.getId())
                .hasFieldOrPropertyWithValue("title", bookOne.getTitle())
                .hasFieldOrPropertyWithValue("description", bookOne.getDescription())
                .hasFieldOrPropertyWithValue("author", bookOne.getAuthor())
                .hasFieldOrPropertyWithValue("imagePath", bookOne.getImagePath())
                .hasFieldOrPropertyWithValue("price", bookOne.getPrice());
    }

    @Test
    void findAllBooks() {
        var books = bookRepository.findAll();
        assertThat(books).containsExactlyInAnyOrder(bookOne, bookTwo);
    }
}
