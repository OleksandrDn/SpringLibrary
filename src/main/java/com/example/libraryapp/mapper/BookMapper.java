package com.example.libraryapp.mapper;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO toDto(Book book) {
        if (book == null) {
            return null;
        }

        BookDTO dto = new BookDTO();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());

        // Map author name
        if (book.getAuthor() != null) {
            dto.setAuthor(book.getAuthor().getName());
        }

        // Map genre name
        if (book.getGenre() != null) {
            dto.setGenre(book.getGenre().getName());
        }

        return dto;
    }

    public Book toEntity(BookDTO dto) {
        if (dto == null) {
            return null;
        }

        Book book = new Book();
        book.setId(dto.getId());
        book.setTitle(dto.getTitle());

        // Note: Author and Genre will be set in the service layer
        // This is because we want to check for existing authors/genres
        // or create new ones if they don't exist

        return book;
    }
}