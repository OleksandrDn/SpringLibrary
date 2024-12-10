package com.example.libraryapp.controller;

import com.example.libraryapp.dto.AuthorDTO;
import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.service.AuthorService;
import com.example.libraryapp.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;


    // Створення нового автора
    @PostMapping
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorDTO dto) {
        return new ResponseEntity<>(authorService.create(dto), HttpStatus.CREATED);
    }

    // Отримання всіх авторів
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> readAll() {
        return new ResponseEntity<>(authorService.readAll(), HttpStatus.OK);
    }

    // Отримання автора за ID
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        return authorService.readById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Пошук авторів за іменем
    @GetMapping("/search")
    public ResponseEntity<List<AuthorDTO>> searchAuthorsByName(@RequestParam String name) {
        return new ResponseEntity<>(authorService.findByName(name), HttpStatus.OK);
    }

    // Оновлення автора
    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        authorDTO.setId(id); // Встановлюємо ID з шляху
        return new ResponseEntity<>(authorService.update(authorDTO), HttpStatus.OK);
    }

    // Видалення автора
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint to get books by author ID
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookDTO>> getBooksByAuthor(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.findBooksByAuthorId(id), HttpStatus.OK);
    }

    @GetMapping("/books/{authorName}")
    public ResponseEntity<List<BookDTO>> getBooksByAuthorName(@PathVariable String authorName) {
        return new ResponseEntity<>(authorService.findBooksByAuthorName(authorName), HttpStatus.OK);
    }
}