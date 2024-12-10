package com.example.libraryapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthorDTO {
    private Long id;

    @NotBlank(message = "Ім'я автора не може бути порожнім")
    @Size(min = 2, max = 100, message = "Ім'я автора має бути від 2 до 100 символів")
    private String name;
}