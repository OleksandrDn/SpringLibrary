package com.example.libraryapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDTO {
    private Long id;

    @NotBlank(message = "Назва книги не може бути порожньою")
    @Size(min = 1, max = 255, message = "Назва книги має бути від 1 до 255 символів")
    private String title;

    @NotBlank(message = "Автор книги є обов'язковим")
    private String author;

    @NotBlank(message = "Жанр книги є обов'язковим")
    private String genre;
}