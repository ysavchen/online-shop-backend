package com.mycompany.online_shop_backend.dto;

import com.mycompany.online_shop_backend.domain.Author;
import com.mycompany.online_shop_backend.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private long id;
    private String title;
    private String description;
    private String author;
    private String image;
    private double price;

    public static BookDto toDto(Book book) {
        return new BookDto(
                book.getId(), book.getTitle(), book.getDescription(),
                book.getAuthor().getFullName(),
                book.getImage(), book.getPrice()
        );
    }

    public static Book toEntity(BookDto dto) {
        return new Book(
                dto.id, dto.title,
                dto.description,
                new Author(0, dto.author),
                dto.image,
                dto.price
        );
    }
}
