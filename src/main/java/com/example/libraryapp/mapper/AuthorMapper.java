package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.AuthorDTO;
import com.example.libraryapp.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    // Перетворення Entity в DTO
    public AuthorDTO toDto(Author author) {
        if (author == null) {
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());

        return authorDTO;
    }

    // Перетворення DTO в Entity
    public Author toEntity(AuthorDTO authorDTO) {
        if (authorDTO == null) {
            return null;
        }

        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());

        return author;
    }
}