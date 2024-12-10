package com.example.libraryapp.repository;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.entity.Book;
import com.example.libraryapp.mapper.BookMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Method to find books by author name
    List<Book> findByAuthor_NameContainingIgnoreCase(String authorName);

    // Method to find books by author ID
    @Query("SELECT b FROM Book b WHERE b.author.id = :authorId")
    List<Book> findByAuthorId(@Param("authorId") Long authorId);

    default List<BookDTO> findBooksByAuthorNameAsDTO(String authorName, BookMapper bookMapper) {
        return findByAuthor_NameContainingIgnoreCase(authorName)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    // Метод для пошуку книг за назвою жанру
    @Query("SELECT b FROM Book b WHERE b.genre.name = :genreName")
    List<Book> findByGenreName(@Param("genreName") String genreName);

    // Перевантажений метод для отримання книг за назвою жанру як DTO
    default List<BookDTO> findBooksByGenreNameAsDTO(String genreName, BookMapper bookMapper) {
        return findByGenreName(genreName)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
    //Метод для перевірки унікальності книги за назвою та іменем автора
    Optional<Book> findByTitleIgnoreCaseAndAuthor_NameIgnoreCase(String title, String authorName);

    List<Book> findByAuthor_Id(Long authorId);
    List<Book> findByAuthor_NameIgnoreCase(String authorName);
    List<Book> findByGenre_NameIgnoreCase(String genreName);

}

