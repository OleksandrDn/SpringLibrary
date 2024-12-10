package com.example.libraryapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GenreDTO {
    private Long id;

    @NotBlank(message = "Назва жанру не може бути порожньою")
    @Size(min = 2, max = 50, message = "Назва жанру має бути від 2 до 50 символів")
    private String name;

    @Size(max = 255, message = "Опис жанру не може перевищувати 255 символів")
    private String description;
}