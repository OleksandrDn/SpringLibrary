//package com.example.libraryapp.controller;
//
//import com.example.libraryapp.dto.AuthorDTO;
//import com.example.libraryapp.service.AuthorService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/authors")
//@AllArgsConstructor
//public class AuthorWebController {
//
//    private final AuthorService authorService;
//
//    // Сторінка з усіма авторами
//    @GetMapping
//    public String listAuthors(Model model) {
//        model.addAttribute("authors", authorService.readAll());
//        return "authors/list";
//    }
//
//    // Сторінка додавання нового автора
//    @GetMapping("/add")
//    public String showAddAuthorForm(Model model) {
//        model.addAttribute("author", new AuthorDTO());
//        return "authors/add";
//    }
//
//    // Обробка додавання автора
//    @PostMapping("/add")
//    public String addAuthor(@ModelAttribute("author") AuthorDTO authorDTO) {
//        authorService.create(authorDTO);
//        return "redirect:/authors";
//    }
//
//    // Сторінка редагування автора
//    @GetMapping("/edit/{id}")
//    public String showEditAuthorForm(@PathVariable Long id, Model model) {
//        AuthorDTO author = authorService.readById(id)
//                .orElseThrow(() -> new RuntimeException("Автор не знайдений"));
//        model.addAttribute("author", author);
//        return "authors/edit";
//    }
//
//    // Обробка редагування автора
//    @PostMapping("/edit")
//    public String editAuthor(@ModelAttribute("author") AuthorDTO authorDTO) {
//        authorService.update(authorDTO);
//        return "redirect:/authors";
//    }
//
//    // Видалення автора
//    @GetMapping("/delete/{id}")
//    public String deleteAuthor(@PathVariable Long id) {
//        authorService.delete(id);
//        return "redirect:/authors";
//    }
//
//    // Сторінка пошуку авторів
//    @GetMapping("/search")
//    public String searchAuthors(@RequestParam(required = false) String name, Model model) {
//        if (name != null && !name.isEmpty()) {
//            model.addAttribute("authors", authorService.findByName(name));
//        } else {
//            model.addAttribute("authors", authorService.readAll());
//        }
//        return "authors/list";
//    }
//}