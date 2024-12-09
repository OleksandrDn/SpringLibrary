package com.example.libraryapp.dto;

import lombok.Data;

@Data
public class BookDTO {
    private Long id;
    private String title;
    private String author; // Змінено на String для зручності введення
    private String genre;  // Змінено на String для зручності введення
}