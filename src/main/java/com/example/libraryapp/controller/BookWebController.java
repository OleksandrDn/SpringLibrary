//package com.example.libraryapp.controller;
//
//import com.example.libraryapp.dto.BookDTO;
//import com.example.libraryapp.service.BookService;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequestMapping("/books")
//@AllArgsConstructor
//public class BookWebController {
//
//    private final BookService bookService;
////    private final GenreService genreService;
//
//    // Сторінка з усіма книгами
//    @GetMapping
//    public String listBooks(Model model) {
//        model.addAttribute("books", bookService.readAll());
//        return "books/list";
//    }
//
//    // Сторінка додавання нової книги
//    @GetMapping("/add")
//    public String showAddBookForm(Model model) {
//        model.addAttribute("book", new BookDTO());
////        model.addAttribute("genres", genreService.readAll());
//        return "books/add";
//    }
//
//    // Обробка додавання книги
//    @PostMapping("/add")
//    public String addBook(@ModelAttribute("book") BookDTO bookDTO) {
//        bookService.create(bookDTO);
//        return "redirect:/books";
//    }
//
//    // Сторінка редагування книги
//    @GetMapping("/edit/{id}")
//    public String showEditBookForm(@PathVariable Long id, Model model) {
//        BookDTO book = bookService.readById(id)
//                .orElseThrow(() -> new RuntimeException("Книга не знайдена"));
//        model.addAttribute("book", book);
////        model.addAttribute("genres", genreService.readAll());
//        return "books/edit";
//    }
//
//    // Обробка редагування книги
//    @PostMapping("/edit")
//    public String editBook(@ModelAttribute("book") BookDTO bookDTO) {
//        bookService.update(bookDTO);
//        return "redirect:/books";
//    }
//
//    // Видалення книги
//    @GetMapping("/delete/{id}")
//    public String deleteBook(@PathVariable Long id) {
//        bookService.delete(id);
//        return "redirect:/books";
//    }
//}