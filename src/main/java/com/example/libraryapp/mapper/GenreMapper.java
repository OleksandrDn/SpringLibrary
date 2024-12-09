package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.GenreDTO;
import com.example.libraryapp.entity.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {

    // Перетворення Entity в DTO
    public GenreDTO toDto(Genre genre) {
        if (genre == null) {
            return null;
        }

        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        genreDTO.setDescription(genre.getDescription());

        return genreDTO;
    }

    // Перетворення DTO в Entity
    public Genre toEntity(GenreDTO genreDTO) {
        if (genreDTO == null) {
            return null;
        }

        Genre genre = new Genre();
        genre.setId(genreDTO.getId());
        genre.setName(genreDTO.getName());
        genre.setDescription(genreDTO.getDescription());

        return genre;
    }
}