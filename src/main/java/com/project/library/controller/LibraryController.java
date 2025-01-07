package com.project.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.library.entity.Book;
import com.project.library.service.LibraryService;

@RestController
public class LibraryController {

    final private LibraryService libraryService;

    public LibraryController(final LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/library/{userId}/assignBook")
    public Book assignBook(@PathVariable("userId") Integer userId, @RequestParam(value="bookId", required=true) Integer bookId) {
        return this.libraryService.assignBook(userId, bookId);
    }

    @GetMapping("/library/{userId}/returnBook")
    public Book returnBook(@PathVariable("userId") Integer userId, @RequestParam(value="bookId", required=true) Integer bookId) {
        return this.libraryService.returnBook(userId, bookId);
    }
}
