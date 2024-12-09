package com.example.libraryapp.controller;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.service.BookService;
//import com.example.libraryapp.service.BookSearchService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookController {

    private final BookService bookService;
//    private final BookSearchService bookSearchService;


    // Створення нової книги
    @PostMapping
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO dto) {
        return new ResponseEntity<>(bookService.create(dto), HttpStatus.CREATED);
    }

    // Отримання всіх книг
    @GetMapping
    public ResponseEntity<List<BookDTO>> readAll() {
        return new ResponseEntity<>(bookService.readAll(), HttpStatus.OK);
    }


    // Отримання книги за ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        return bookService.readById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Оновлення книги
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        bookDTO.setId(id); // Встановлюємо ID з шляху
        return new ResponseEntity<>(bookService.update(bookDTO), HttpStatus.OK);
    }

    // Видалення книги
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Пошук книг за назвою, автором або жанром
//    @GetMapping("/search")
//    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam String query) {
//        return new ResponseEntity<>(bookSearchService.searchBooks(query), HttpStatus.OK);
//    }
}