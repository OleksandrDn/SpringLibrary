//package com.example.libraryapp.service;
//
//import com.example.libraryapp.dto.BookDTO;
////import com.example.libraryapp.dto.BookSearchDTO;
//import com.example.libraryapp.mapper.BookMapper;
//import com.example.libraryapp.repository.BookRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class BookSearchService {
//    private final BookRepository bookRepository;
//    private final BookMapper bookMapper;
//
//    public List<BookDTO> searchBooks(String searchTerm) {
//        return bookRepository.searchBooks(searchTerm).stream()
//                .map(book -> {
//                    BookDTO searchDTO = new BookDTO();
//                    searchDTO.setId(book.getId());
//                    searchDTO.setTitle(book.getTitle());
//                    searchDTO.setAuthor(book.getAuthor() != null ? book.getAuthor().getName() : null);
//                    searchDTO.setGenre(book.getGenre() != null ? book.getGenre().getName() : null);
//                    return searchDTO;
//                })
//                .collect(Collectors.toList());
//    }
//}