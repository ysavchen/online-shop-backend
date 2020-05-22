package com.mycompany.online_shop_demo_backend.controllers.rest;

import com.mycompany.online_shop_demo_backend.dto.BookDto;
import com.mycompany.online_shop_demo_backend.exceptions.EntityNotFoundException;
import com.mycompany.online_shop_demo_backend.service.db.BookDbService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookDbService dbService;

    @ApiOperation("Gets all books")
    @GetMapping(path = "/api/books",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookDto> getBooks() {
        return dbService.getAllBooks().stream()
                .map(BookDto::toDto)
                .collect(toList());
    }

    @ApiOperation("Gets a book with id")
    @GetMapping(path = "/api/books/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BookDto getBookById(@PathVariable("id") long id) {
        return dbService.getById(id)
                .map(BookDto::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Book with id = " + id + " is not found"));
    }
}