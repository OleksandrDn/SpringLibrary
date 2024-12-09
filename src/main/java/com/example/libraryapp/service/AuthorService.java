package com.example.libraryapp.service;

import com.example.libraryapp.dto.AuthorDTO;
import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.entity.Author;
import com.example.libraryapp.mapper.AuthorMapper;
import com.example.libraryapp.mapper.BookMapper;
import com.example.libraryapp.repository.AuthorRepository;
import com.example.libraryapp.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;


    // Створення нового автора
    public AuthorDTO create(AuthorDTO dto) {
        Author author = authorMapper.toEntity(dto);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toDto(savedAuthor);
    }

    // Отримання списку всіх авторів
    public List<AuthorDTO> readAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    // Пошук автора за ID
    public Optional<AuthorDTO> readById(Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::toDto);
    }

    // Пошук авторів за ім'ям
    public List<AuthorDTO> findByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name).stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    // Оновлення інформації про автора
    public AuthorDTO update(AuthorDTO authorDTO) {
        Optional<Author> existingAuthorOptional = authorRepository.findById(authorDTO.getId());

        if (existingAuthorOptional.isPresent()) {
            Author existingAuthor = existingAuthorOptional.get();

            // Оновлення полів автора
            existingAuthor.setName(authorDTO.getName());
            // Додайте інші поля, які потрібно оновити

            Author updatedAuthor = authorRepository.save(existingAuthor);
            return authorMapper.toDto(updatedAuthor);
        } else {
            throw new RuntimeException("Автор з ID " + authorDTO.getId() + " не знайдений");
        }
    }

    // Видалення автора
    public void delete(Long id) {
        // Перевірка чи існує автор перед видаленням
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Автор з ID " + id + " не знайдений");
        }
        authorRepository.deleteById(id);
    }

    public List<BookDTO> findBooksByAuthorName(String authorName) {
        return bookRepository.findBooksByAuthorNameAsDTO(authorName, bookMapper);
    }
}