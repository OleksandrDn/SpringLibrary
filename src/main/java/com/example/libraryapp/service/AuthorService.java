package com.example.libraryapp.service;

import com.example.libraryapp.dto.AuthorDTO;
import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.entity.Author;
import com.example.libraryapp.mapper.AuthorMapper;
import com.example.libraryapp.mapper.BookMapper;
import com.example.libraryapp.repository.AuthorRepository;
import com.example.libraryapp.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    @Transactional
    public AuthorDTO create(AuthorDTO dto) {
        // Перевірка на унікальність імені автора
        if (authorRepository.findByNameIgnoreCase(dto.getName()).isPresent()) {
            throw new RuntimeException("Автор з таким ім'ям вже існує");
        }

        Author author = authorMapper.toEntity(dto);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toDto(savedAuthor);
    }

    public List<AuthorDTO> readAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<AuthorDTO> readById(Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::toDto);
    }

    public List<AuthorDTO> findByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name).stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AuthorDTO update(AuthorDTO authorDTO) {
        Optional<Author> existingAuthorOptional = authorRepository.findById(authorDTO.getId());

        if (existingAuthorOptional.isPresent()) {
            Author existingAuthor = existingAuthorOptional.get();
            existingAuthor.setName(authorDTO.getName());

            Author updatedAuthor = authorRepository.save(existingAuthor);
            return authorMapper.toDto(updatedAuthor);
        } else {
            throw new RuntimeException("Автор з ID " + authorDTO.getId() + " не знайдений");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Автор з ID " + id + " не знайдений");
        }
        authorRepository.deleteById(id);
    }


    public List<BookDTO> findBooksByAuthorName(String authorName) {
        return bookRepository.findByAuthor_NameIgnoreCase(authorName).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}