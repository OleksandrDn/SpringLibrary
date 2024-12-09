package com.example.libraryapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {
    private Long id;
    private String name;
    // Додайте інші поля, якщо потрібно, наприклад:
    // private String biography;
    // private LocalDate birthDate;
}