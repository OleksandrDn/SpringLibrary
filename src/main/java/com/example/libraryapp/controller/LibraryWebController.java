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
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web")
@AllArgsConstructor
public class LibraryWebController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    // Book-related web routes
    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.readAll());
        model.addAttribute("newBook", new BookDTO());
        return "books/list";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute("newBook") BookDTO bookDTO) {
        bookService.create(bookDTO);
        return "redirect:/web/books";
    }

    @GetMapping("/books/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        bookService.readById(id).ifPresent(book -> {
            model.addAttribute("book", book);
            model.addAttribute("authors", authorService.readAll());
            model.addAttribute("genres", genreService.readAll());
        });
        return "books/edit";
    }

    @PostMapping("/books/update")
    public String updateBook(@ModelAttribute("book") BookDTO bookDTO) {
        bookService.update(bookDTO);
        return "redirect:/web/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/web/books";
    }

    // Author-related web routes
    @GetMapping("/authors")
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.readAll());
        model.addAttribute("newAuthor", new AuthorDTO());
        return "authors/list";
    }

    @PostMapping("/authors/add")
    public String addAuthor(@ModelAttribute("newAuthor") AuthorDTO authorDTO) {
        authorService.create(authorDTO);
        return "redirect:/web/authors";
    }

    @GetMapping("/authors/edit/{id}")
    public String editAuthorForm(@PathVariable Long id, Model model) {
        authorService.readById(id).ifPresent(author ->
                model.addAttribute("author", author)
        );
        return "authors/edit";
    }

    @PostMapping("/authors/update")
    public String updateAuthor(@ModelAttribute("author") AuthorDTO authorDTO) {
        authorService.update(authorDTO);
        return "redirect:/web/authors";
    }

    @GetMapping("/authors/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/web/authors";
    }

    // Genre-related web routes
    @GetMapping("/genres")
    public String listGenres(Model model) {
        model.addAttribute("genres", genreService.readAll());
        model.addAttribute("newGenre", new GenreDTO());
        return "genres/list";
    }

    @PostMapping("/genres/add")
    public String addGenre(@ModelAttribute("newGenre") GenreDTO genreDTO) {
        genreService.create(genreDTO);
        return "redirect:/web/genres";
    }

    @GetMapping("/genres/edit/{id}")
    public String editGenreForm(@PathVariable Long id, Model model) {
        genreService.readById(id).ifPresent(genre ->
                model.addAttribute("genre", genre)
        );
        return "genres/edit";
    }

    @PostMapping("/genres/update")
    public String updateGenre(@ModelAttribute("genre") GenreDTO genreDTO) {
        genreService.update(genreDTO);
        return "redirect:/web/genres";
    }

    @GetMapping("/genres/delete/{id}")
    public String deleteGenre(@PathVariable Long id) {
        genreService.delete(id);
        return "redirect:/web/genres";
    }

    // Home/Index page
    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }
}