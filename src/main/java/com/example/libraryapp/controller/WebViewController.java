package com.example.libraryapp.controller;

import com.example.libraryapp.dto.AuthorDTO;
import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.dto.GenreDTO;
import com.example.libraryapp.service.AuthorService;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class WebViewController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping
    public String homePage(Model model) {
        return "index";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        List<BookDTO> books = bookService.readAll();
        model.addAttribute("books", books);
        return "books/list";
    }

    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable Long id, Model model) {
        BookDTO book = bookService.readById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        model.addAttribute("book", book);
        return "books/details";
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        List<AuthorDTO> authors = authorService.readAll();
        model.addAttribute("authors", authors);
        return "authors/list";
    }

    @GetMapping("/authors/{id}")
    public String authorDetails(@PathVariable Long id, Model model) {
        AuthorDTO author = authorService.readById(id)
                .orElseThrow(() -> new RuntimeException("Author not found"));
        List<BookDTO> books = bookService.findBooksByAuthorId(id);
        model.addAttribute("author", author);
        model.addAttribute("books", books);
        return "authors/details";
    }

    @GetMapping("/genres")
    public String listGenres(Model model) {
        List<GenreDTO> genres = genreService.readAll();
        model.addAttribute("genres", genres);
        return "genres/list";
    }

//   @GetMapping("/genres/{id}")
//    public String genreDetails(@PathVariable Long id, Model model) {
//        GenreDTO genre = genreService.readById(id)
//                .orElseThrow(() -> new RuntimeException("Genre not found"));
//        List<BookDTO> books = genreService.findBooksByGenreId(id);
//        model.addAttribute("genre", genre);
//        model.addAttribute("books", books);
//        return "genres/details";
//    }
}