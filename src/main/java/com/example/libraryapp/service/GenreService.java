package com.example.libraryapp.service;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.dto.GenreDTO;
import com.example.libraryapp.entity.Genre;
import com.example.libraryapp.mapper.BookMapper;
import com.example.libraryapp.mapper.GenreMapper;
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
public class GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Transactional
    public GenreDTO create(GenreDTO dto) {
        // Перевірка на унікальність назви жанру
        if (genreRepository.findByNameIgnoreCase(dto.getName()).isPresent()) {
            throw new RuntimeException("Жанр з такою назвою вже існує");
        }

        Genre genre = genreMapper.toEntity(dto);
        Genre savedGenre = genreRepository.save(genre);
        return genreMapper.toDto(savedGenre);
    }

    public List<GenreDTO> readAll() {
        return genreRepository.findAll().stream()
                .map(genreMapper::toDto)
                .collect(Collectors.toList());
    }

    public Optional<GenreDTO> readById(Long id) {
        return genreRepository.findById(id)
                .map(genreMapper::toDto);
    }

    public List<GenreDTO> findByName(String name) {
        return genreRepository.findByNameContainingIgnoreCase(name).stream()
                .map(genreMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public GenreDTO update(GenreDTO genreDTO) {
        Optional<Genre> existingGenreOptional = genreRepository.findById(genreDTO.getId());

        if (existingGenreOptional.isPresent()) {
            Genre existingGenre = existingGenreOptional.get();
            existingGenre.setName(genreDTO.getName());
            existingGenre.setDescription(genreDTO.getDescription());

            Genre updatedGenre = genreRepository.save(existingGenre);
            return genreMapper.toDto(updatedGenre);
        } else {
            throw new RuntimeException("Жанр з ID " + genreDTO.getId() + " не знайдений");
        }
    }

    @Transactional
    public void delete(Long id) {
        if (!genreRepository.existsById(id)) {
            throw new RuntimeException("Жанр з ID " + id + " не знайдений");
        }
        genreRepository.deleteById(id);
    }

    public List<BookDTO> findBooksByGenreName(String genreName) {
        return bookRepository.findByGenre_NameIgnoreCase(genreName).stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}