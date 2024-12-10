package com.example.libraryapp.service;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.entity.Author;
import com.example.libraryapp.entity.Book;
import com.example.libraryapp.entity.Genre;
import com.example.libraryapp.mapper.BookMapper;
import com.example.libraryapp.repository.AuthorRepository;
import com.example.libraryapp.repository.BookRepository;
import com.example.libraryapp.repository.GenreRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
        // Перевірка наявності назви
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new RuntimeException("Книга повинна мати назву");
        }

        // Перевірка автора
        Author author = authorRepository.findByNameIgnoreCase(dto.getAuthor())
                .orElseThrow(() -> new RuntimeException("Автор з ім'ям " + dto.getAuthor() + " не існує"));

        // Перевірка унікальності книги (назва + автор)
        Optional<Book> existingBook = bookRepository.findByTitleIgnoreCaseAndAuthor_NameIgnoreCase(
                dto.getTitle(),
                dto.getAuthor()
        );

        if (existingBook.isPresent()) {
            throw new RuntimeException("Книга з назвою '" + dto.getTitle() + "' від автора '" + dto.getAuthor() + "' вже існує");
        }

        // Перевірка жанру
        Genre genre = genreRepository.findByNameIgnoreCase(dto.getGenre())
                .orElseThrow(() -> new RuntimeException("Жанр з назвою " + dto.getGenre() + " не існує"));

        // Створення книги
        Book book = bookMapper.toEntity(dto);
        book.setAuthor(author);
        book.setGenre(genre);

        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    public List<BookDTO> readAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<BookDTO> readById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto);
    }

    @Transactional
    public BookDTO update(BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(bookDTO.getId())
                .orElseThrow(() -> new RuntimeException("Книга з ID " + bookDTO.getId() + " не знайдена"));

        // Оновлення назви, якщо вона надана
        if (bookDTO.getTitle() != null && !bookDTO.getTitle().trim().isEmpty()) {
            existingBook.setTitle(bookDTO.getTitle());
        }

        // Оновлення автора, якщо він наданий
        if (bookDTO.getAuthor() != null) {
            Author author = authorRepository.findByNameIgnoreCase(bookDTO.getAuthor())
                    .orElseThrow(() -> new RuntimeException("Автор з ім'ям " + bookDTO.getAuthor() + " не існує"));
            existingBook.setAuthor(author);
        }

        // Оновлення жанру, якщо він наданий
        if (bookDTO.getGenre() != null) {
            Genre genre = genreRepository.findByNameIgnoreCase(bookDTO.getGenre())
                    .orElseThrow(() -> new RuntimeException("Жанр з назвою " + bookDTO.getGenre() + " не існує"));
            existingBook.setGenre(genre);
        }

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDto(updatedBook);
    }

    @Transactional
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Книга з ID " + id + " не знайдена");
        }
        bookRepository.deleteById(id);
    }

    public List<BookDTO> findBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthor_Id(authorId).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}