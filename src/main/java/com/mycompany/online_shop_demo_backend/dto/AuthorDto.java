package com.mycompany.online_shop_demo_backend.dto;

import com.mycompany.online_shop_demo_backend.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AuthorDto {

    private long id;
    private String fullName;

    public static Author toDomainObject(AuthorDto dto) {
        return new Author(dto.id, dto.fullName);
    }

    public static AuthorDto toDto(Author author) {
        return new AuthorDto(author.getId(), author.getFullName());
    }
}
