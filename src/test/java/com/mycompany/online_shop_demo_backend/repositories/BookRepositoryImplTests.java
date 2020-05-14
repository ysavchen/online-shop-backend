package com.mycompany.online_shop_demo_backend.repositories;

import com.mycompany.online_shop_demo_backend.domain.Author;
import com.mycompany.online_shop_demo_backend.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BookRepositoryImplTests {

//    private final Author prattAuthor = new Author(1, "Philip", "Pratt");
//    private final Author learnAuthor = new Author(2, "Michael", "Learn");
//
//    private final Book guideBook = new Book(1, "A Guide to SQL", prattAuthor);
//    private final Book conceptsBook = new Book(2, "Concepts of Database Management", prattAuthor);
//    private final Book sqlCodingBook = new Book(3, "SQL Programming and Coding", learnAuthor);
//    private static final long NON_EXISTING_ID = 50;
//
//    @Autowired
//    private BookRepository bookRepository;
//
//    @Autowired
//    private TestEntityManager em;
//
//    @Test
//    void saveBook() {
//        var book = new Book("test");
//        long id = bookRepository.save(book).getId();
//        assertTrue(id > 0, "Invalid id for an saved Book");
//    }
//
//    @Test
//    void saveBookWithGenre() {
//        var genre = new Genre("test genre");
//        var bookWithGenre = new Book("test").setGenre(genre);
//        long id = bookRepository.save(bookWithGenre).getId();
//        assertTrue(id > 0,
//                "Book with genre is not saved");
//    }
//
//    @Test
//    void saveBookWithAuthor() {
//        var testAuthor = new Author("testName", "testSurname");
//        var bookWithAuthor = new Book("test").setAuthor(testAuthor);
//        long id = bookRepository.save(bookWithAuthor).getId();
//        assertTrue(id > 0,
//                "Book with author is not saved");
//
//    }
//
//    @Test
//    void saveBookWithGenreAndAuthor() {
//        var testGenre = new Genre("test genre");
//        var testAuthor = new Author("testName", "testSurname");
//        var bookWithBoth = new Book("test").setAuthor(testAuthor).setGenre(testGenre);
//
//        long id = bookRepository.save(bookWithBoth).getId();
//        assertTrue(id > 0,
//                "Book with genre and author is not saved");
//    }
//
//    @Test
//    void findBookById() {
//        assertThat(bookRepository.findById(guideBook.getId())).get()
//                .hasFieldOrPropertyWithValue("id", guideBook.getId())
//                .hasFieldOrPropertyWithValue("title", guideBook.getTitle())
//                .hasFieldOrPropertyWithValue("author", guideBook.getAuthor())
//                .hasFieldOrPropertyWithValue("genre", guideBook.getGenre());
//    }
//
//    @Test
//    void findBookByNonExistingId() {
//        assertThat(bookRepository.findById(NON_EXISTING_ID)).isEmpty();
//    }
//
//    @Test
//    void findBookWithGenre() {
//        var testGenre = new Genre("test genre");
//        var book = new Book("test").setGenre(testGenre);
//        long id = bookRepository.save(book).getId();
//
//        em.clear();
//        assertThat(bookRepository.findById(id)).get()
//                .hasFieldOrPropertyWithValue("id", id)
//                .hasFieldOrPropertyWithValue("title", book.getTitle())
//                .hasFieldOrPropertyWithValue("author", null)
//                .hasFieldOrPropertyWithValue("genre", testGenre);
//    }
//
//    @Test
//    void findBookWithAuthor() {
//        var testAuthor = new Author("testName", "testSurname");
//        var book = new Book("test").setAuthor(testAuthor);
//        long id = bookRepository.save(book).getId();
//
//        em.clear();
//        assertThat(bookRepository.findById(id)).get()
//                .hasFieldOrPropertyWithValue("id", id)
//                .hasFieldOrPropertyWithValue("title", book.getTitle())
//                .hasFieldOrPropertyWithValue("author", testAuthor)
//                .hasFieldOrPropertyWithValue("genre", null);
//    }
//
//    @Test
//    void findBookWithGenreAndAuthor() {
//        var testGenre = new Genre("test genre");
//        var testAuthor = new Author("testName", "testSurname");
//        var book = new Book("test").setAuthor(testAuthor).setGenre(testGenre);
//
//        em.clear();
//        long id = bookRepository.save(book).getId();
//        assertThat(bookRepository.findById(id)).get()
//                .hasFieldOrPropertyWithValue("id", id)
//                .hasFieldOrPropertyWithValue("title", book.getTitle())
//                .hasFieldOrPropertyWithValue("author", testAuthor)
//                .hasFieldOrPropertyWithValue("genre", testGenre);
//    }
//
//    @Test
//    void updateBookTitle() {
//        var newTitle = "newTitle";
//        bookRepository.updateTitle(guideBook.getId(), newTitle);
//
//        em.clear();
//        assertThat(bookRepository.findById(guideBook.getId())).get()
//                .hasFieldOrPropertyWithValue("title", newTitle);
//    }
//
//    @Test
//    void deleteById() {
//        bookRepository.deleteById(guideBook.getId());
//        assertNull(em.find(Book.class, guideBook.getId()),
//                "Book is not deleted by id");
//    }
//
//    @Test
//    void findAllBooks() {
//        var books = bookRepository.findAll();
//        assertThat(books).containsExactlyInAnyOrder(guideBook, conceptsBook, sqlCodingBook);
//    }
}
