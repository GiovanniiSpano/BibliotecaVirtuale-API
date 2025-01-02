package com.project.library.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.project.library.entity.Book;
import com.project.library.service.LibraryService;

@RestController
public class LibraryController {

    final private LibraryService libraryService;

    public LibraryController(final LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books")
    public Iterable<Book> getAllBooks() {
        return this.libraryService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") Integer id) {
        return this.libraryService.getBookById(id);
    }

    @GetMapping("/books") 
    public List<Book> searchBooks(@RequestParam(name="author", required=false) String author, @RequestParam(name="genre", required=false) String genre, @RequestParam(name="isAvailable", required=false) Boolean isAvailable) {
        return this.libraryService.searchBooks(author, genre, isAvailable);
    }

    @PostMapping("/books/addBook")
    public Book createBook(@RequestBody Book book) {
        return this.libraryService.createBook(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book b) {
        return this.libraryService.updateBook(id, b);
    }

    @DeleteMapping("book/{id}")
    public Book removeBook(@PathVariable("id") Integer id) throws ResponseStatusException {
        return this.libraryService.removeBook(id);
    }
}
