package com.example.libraryapp.controller;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.dto.GenreDTO;
import com.example.libraryapp.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
@AllArgsConstructor
public class GenreController {

    private final GenreService genreService;

    // Створення нового жанру
    @PostMapping
    public ResponseEntity<GenreDTO> create(@RequestBody GenreDTO dto) {
        return new ResponseEntity<>(genreService.create(dto), HttpStatus.CREATED);
    }

    // Отримання всіх жанрів
    @GetMapping
    public ResponseEntity<List<GenreDTO>> readAll() {
        return new ResponseEntity<>(genreService.readAll(), HttpStatus.OK);
    }

    // Отримання жанру за ID
    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable Long id) {
        return genreService.readById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Пошук жанрів за назвою
    @GetMapping("/search")
    public ResponseEntity<List<GenreDTO>> searchGenresByName(@RequestParam String name) {
        return new ResponseEntity<>(genreService.findByName(name), HttpStatus.OK);
    }

    // Оновлення жанру
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO genreDTO) {
        genreDTO.setId(id); // Встановлюємо ID з шляху
        return new ResponseEntity<>(genreService.update(genreDTO), HttpStatus.OK);
    }

    // Видалення жанру
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        genreService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/books/{genreName}")
    public ResponseEntity<List<BookDTO>> getBooksByGenreName(@PathVariable String genreName) {
        return new ResponseEntity<>(genreService.findBooksByGenreName(genreName), HttpStatus.OK);
    }
}