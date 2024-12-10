package com.example.libraryapp.web;

import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.service.AuthorService;
import com.example.libraryapp.service.BookService;
import com.example.libraryapp.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/books")
@AllArgsConstructor
public class BookWebController {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    @GetMapping
    public String listBooks(Model model) {
        List<BookDTO> books = bookService.readAll();
        model.addAttribute("books", books);
        model.addAttribute("newBook", new BookDTO());
        model.addAttribute("authors", authorService.readAll());
        model.addAttribute("genres", genreService.readAll());
        return "books/list";
    }

    @PostMapping("/create")
    public String createBook(@ModelAttribute BookDTO bookDTO, RedirectAttributes redirectAttributes) {
        try {
            bookService.create(bookDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Книгу успішно створено");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        BookDTO book = bookService.readById(id)
                .orElseThrow(() -> new RuntimeException("Книгу не знайдено"));
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.readAll());
        model.addAttribute("genres", genreService.readAll());
        return "books/edit";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute BookDTO bookDTO, RedirectAttributes redirectAttributes) {
        try {
            bookService.update(bookDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Книгу успішно оновлено");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            bookService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Книгу успішно видалено");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/books";
    }
}