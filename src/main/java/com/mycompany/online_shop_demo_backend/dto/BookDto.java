package com.mycompany.online_shop_demo_backend.dto;

import com.mycompany.online_shop_demo_backend.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BookDto {

    private long id;
    private String title;
    private String description;
    private AuthorDto author;
    private String image;
    private double price;

    public static Book toDomainObject(BookDto dto) {
        return new Book(
                dto.id, dto.title, dto.description,
                AuthorDto.toDomainObject(dto.author),
                dto.image, dto.price
        );
    }

    public static BookDto toDto(Book book) {
        return new BookDto(
                book.getId(), book.getTitle(), book.getDescription(),
                AuthorDto.toDto(book.getAuthor()),
                book.getImage(), book.getPrice()
        );
    }
}
