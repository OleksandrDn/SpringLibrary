package com.example.libraryapp.web;

import com.example.libraryapp.dto.GenreDTO;
import com.example.libraryapp.dto.BookDTO;
import com.example.libraryapp.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/genres")
@AllArgsConstructor
public class GenreWebController {
    private final GenreService genreService;

    @GetMapping
    public String listGenres(Model model) {
        List<GenreDTO> genres = genreService.readAll();
        model.addAttribute("genres", genres);
        model.addAttribute("newGenre", new GenreDTO());
        return "genres/list";
    }

    @PostMapping("/create")
    public String createGenre(@ModelAttribute GenreDTO genreDTO, RedirectAttributes redirectAttributes) {
        try {
            genreService.create(genreDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Жанр успішно створено");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/genres";
    }

    @GetMapping("/edit/{id}")
    public String editGenreForm(@PathVariable Long id, Model model) {
        GenreDTO genre = genreService.readById(id)
                .orElseThrow(() -> new RuntimeException("Жанр не знайдено"));
        model.addAttribute("genre", genre);
        return "genres/edit";
    }

    @PostMapping("/update")
    public String updateGenre(@ModelAttribute GenreDTO genreDTO, RedirectAttributes redirectAttributes) {
        try {
            genreService.update(genreDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Жанр успішно оновлено");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/genres";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            genreService.delete(id);
            redirectAttributes.addFlashAttribute("successMessage", "Жанр успішно видалено");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/genres";
    }

    @GetMapping("/books/{genreName}")
    public String listBooksByGenre(@PathVariable String genreName, Model model) {
        List<BookDTO> books = genreService.findBooksByGenreName(genreName);
        model.addAttribute("books", books);
        model.addAttribute("genreName", genreName);
        return "genres/books";
    }
}