package com.example.libraryapp.repository;

import com.example.libraryapp.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    // Method to find a genre by exact name (case-insensitive)
    Optional<Genre> findByNameIgnoreCase(String name);

    // Method to find genres by name containing a string (case-insensitive)
    // Method to find genres by name containing a string (case-insensitive)

    List<Genre> findByNameContainingIgnoreCase(String name);
}