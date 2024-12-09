package com.example.libraryapp.service;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.entity.Book;
import com.example.libraryapp.entity.Author;
import com.example.libraryapp.entity.Genre;
import com.example.libraryapp.mapper.BookMapper;
import com.example.libraryapp.repository.BookRepository;
import com.example.libraryapp.repository.AuthorRepository;
import com.example.libraryapp.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    @Transactional
    public BookDTO create(BookDTO dto) {
        Book book = bookMapper.toEntity(dto);

        // Обробка автора
        if (dto.getAuthor() != null && !dto.getAuthor().trim().isEmpty()) {
            Optional<Author> existingAuthor = authorRepository.findByNameIgnoreCase(dto.getAuthor().trim());
            book.setAuthor(existingAuthor.orElseGet(() -> {
                Author newAuthor = new Author();
                newAuthor.setName(dto.getAuthor().trim());
                return authorRepository.save(newAuthor);
            }));
        } else {
            book.setAuthor(null);
        }

        // Обробка жанру
        if (dto.getGenre() != null && !dto.getGenre().trim().isEmpty()) {
            Optional<Genre> existingGenre = genreRepository.findByNameIgnoreCase(dto.getGenre().trim());
            book.setGenre(existingGenre.orElseGet(() -> {
                Genre newGenre = new Genre();
                newGenre.setName(dto.getGenre().trim());
                return genreRepository.save(newGenre);
            }));
        } else {
            book.setGenre(null);
        }

        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    public List<BookDTO> readAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    // Метод для отримання книги за ID
    public Optional<BookDTO> readById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto);
    }

    // Метод для оновлення книги
    @Transactional
    public BookDTO update(BookDTO bookDTO) {
        // Перевіряємо, чи існує книга з таким ID
        Book existingBook = bookRepository.findById(bookDTO.getId())
                .orElseThrow(() -> new RuntimeException("Книга з ID " + bookDTO.getId() + " не знайдена"));

        // Оновлення назви тільки якщо вона передана
        if (bookDTO.getTitle() != null && !bookDTO.getTitle().trim().isEmpty()) {
            existingBook.setTitle(bookDTO.getTitle());
        }

        // Обробка автора
        if (bookDTO.getAuthor() != null) {
            if (!bookDTO.getAuthor().trim().isEmpty()) {
                Optional<Author> existingAuthor = authorRepository.findByNameIgnoreCase(bookDTO.getAuthor().trim());
                existingBook.setAuthor(existingAuthor.orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(bookDTO.getAuthor().trim());
                    return authorRepository.save(newAuthor);
                }));
            } else {
                existingBook.setAuthor(null);
            }
        }

        // Обробка жанру
        if (bookDTO.getGenre() != null) {
            if (!bookDTO.getGenre().trim().isEmpty()) {
                Optional<Genre> existingGenre = genreRepository.findByNameIgnoreCase(bookDTO.getGenre().trim());
                existingBook.setGenre(existingGenre.orElseGet(() -> {
                    Genre newGenre = new Genre();
                    newGenre.setName(bookDTO.getGenre().trim());
                    return genreRepository.save(newGenre);
                }));
            } else {
                existingBook.setGenre(null);
            }
        }

        // Збереження оновленої книги
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDto(updatedBook);
    }

    // Метод для видалення книги
    @Transactional
    public void delete(Long id) {
        // Перевіряємо, чи існує книга з таким ID перед видаленням
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Книга з ID " + id + " не знайдена");
        }
        bookRepository.deleteById(id);
    }

    public List<BookDTO> findBooksByAuthorId(Long authorId) {
        List<Book> books = bookRepository.findByAuthorId(authorId);
        return books.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}