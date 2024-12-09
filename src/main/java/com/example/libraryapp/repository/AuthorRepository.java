package com.example.libraryapp.repository;

import com.example.libraryapp.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.libraryapp.entity.Book;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Method to find an author by exact name (case-insensitive)
    Optional<Author> findByNameIgnoreCase(String name);

    // Method to find authors by name containing a string (case-insensitive)
    List<Author> findByNameContainingIgnoreCase(String name);
}