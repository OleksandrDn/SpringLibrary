package com.example.libraryapp.web;

import com.example.libraryapp.dto.AuthorDTO;
import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/authors")
@AllArgsConstructor
public class AuthorWebController {
    private final AuthorService authorService;

    @GetMapping
    public String listAuthors(Model model) {
        List<AuthorDTO> authors = authorService.readAll();
        model.addAttribute("authors", authors);
        model.addAttribute("newAuthor", new AuthorDTO());
        return "authors/list";
    }

    @PostMapping("/create")
    public String createAuthor(@ModelAttribute AuthorDTO authorDTO, RedirectAttributes redirectAttributes) {
        try {
            authorService.create(authorDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Автора успішно створено");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String editAuthorForm(@PathVariable Long id, Model model) {
        AuthorDTO author = authorService.readById(id)
                .orElseThrow(() -> new RuntimeException("Автора не знайдено"));
        model.addAttribute("author", author);
        return "authors/edit";
    }

    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute AuthorDTO authorDTO, RedirectAttributes redirectAttributes) {
        try {
            authorService.update(authorDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Автора успішно оновлено");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            authorService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Автора успішно видалено");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/authors";
    }

    @GetMapping("/books/{authorName}")
    public String listBooksByAuthor(@PathVariable String authorName, Model model) {
        List<BookDTO> books = authorService.findBooksByAuthorName(authorName);
        model.addAttribute("books", books);
        model.addAttribute("authorName", authorName);
        return "authors/books";
    }
}