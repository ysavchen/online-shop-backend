package com.mycompany.online_shop_demo_backend.controllers;

import com.google.gson.Gson;
import com.mycompany.online_shop_demo_backend.domain.Author;
import com.mycompany.online_shop_demo_backend.domain.Book;
import com.mycompany.online_shop_demo_backend.dto.BookDto;
import com.mycompany.online_shop_demo_backend.security.JwtProvider;
import com.mycompany.online_shop_demo_backend.service.db.BookDbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTests {

    private final Author authorOne = new Author(1, "Author One");
    private final Author authorTwo = new Author(2, "Author Two");

    private final Book bookOne = new Book(1, "Book One", "Description One", authorOne, "/imageOne", 22.95);
    private final Book bookTwo = new Book(2, "Book Two", "Description Two", authorTwo, "/imageTwo", 46.00);
    private final List<Book> books = List.of(bookOne, bookTwo);

    private final BookDto bookOneDto = BookDto.toDto(bookOne);
    private final BookDto bookTwoDto = BookDto.toDto(bookTwo);
    private final List<BookDto> bookDtos = List.of(bookOneDto, bookTwoDto);
    private static final long NON_EXISTING_ID = 50;

    private final Gson gson = new Gson();

    @Configuration
    static class TestConfiguration {

        @Bean
        public JwtProvider jwtProvider() {
            return new JwtProvider("test-secret", 6000);
        }
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookDbService dbService;

    //todo: add some users to test-db
    @WithMockUser(username = "john.doe@test.com")
    @Test
    public void getBooks() throws Exception {
        when(dbService.getAllBooks()).thenReturn(books);
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(bookDtos)));
    }

    @WithMockUser(username = "user@test.com", password = "test", roles = "USER")
    @Test
    public void getBookById() throws Exception {
        when(dbService.getById(bookOne.getId())).thenReturn(Optional.of(bookOne));
        mockMvc.perform(get("/api/books/{id}", bookOneDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(bookOneDto)));
    }

    @WithMockUser(username = "user@test.com", password = "test", roles = "USER")
    @Test
    public void getBookByIdNegative() throws Exception {
        when(dbService.getById(NON_EXISTING_ID)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/books/{id}", NON_EXISTING_ID))
                .andExpect(status().isNotFound());
    }
}
