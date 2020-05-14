package com.mycompany.online_shop_demo_backend.controllers;

import com.mycompany.online_shop_demo_backend.domain.Author;
import com.mycompany.online_shop_demo_backend.domain.Book;
import com.mycompany.online_shop_demo_backend.dto.BookDto;
import com.mycompany.online_shop_demo_backend.service.BookDbService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(BookController.class)
public class BookControllerTests {

//    private final Genre genre = new Genre(1, "Computers & Technology");
//    private final Author prattAuthor = new Author(1, "Philip", "Pratt");
//    private final Author learnAuthor = new Author(2, "Michael", "Learn");
//    private final Book guideBook = new Book(1, "A Guide to SQL", prattAuthor, genre);
//    private final Book conceptsBook = new Book(2, "Concepts of Database Management", prattAuthor, genre);
//    private final Book sqlCodingBook = new Book(3, "SQL Programming and Coding", learnAuthor, genre);
//    private final List<Book> books = List.of(guideBook, conceptsBook, sqlCodingBook);
//
//    private final BookDto guideBookDto = BookDto.toDto(guideBook);
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BookDbService dbService;
//
//    @Test
//    public void getBookById() throws Exception {
//        when(dbService.getById(guideBook.getId())).thenReturn(Optional.of(guideBook));
//        mockMvc.perform(get("/book/{id}", guideBookDto.getId()))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("book", hasProperty("id", is(guideBookDto.getId()))))
//                .andExpect(model().attribute("book", hasProperty("title", is(guideBookDto.getTitle()))))
//                .andExpect(model().attribute("book", hasProperty("author", is(guideBookDto.getAuthor()))))
//                .andExpect(model().attribute("book", hasProperty("genre", is(guideBookDto.getGenre()))));
//    }
//
//    @ParameterizedTest
//    @ValueSource(strings = {"/book/", "/"})
//    public void getAllBooks(String url) throws Exception {
//        when(dbService.getAllBooks()).thenReturn(books);
//        mockMvc.perform(get(url))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    public void showAddForm() throws Exception {
//        mockMvc.perform(get("/book/new"))
//                .andExpect(status().isOk());
//    }
}
