package com.project.library.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

import io.micrometer.common.util.StringUtils;

@RestController
public class LibraryController {

    final private LibraryService libraryService;

    public LibraryController(final LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books")
    public Page<Book> getAllBooks(@RequestParam(value="offset", required=false) Integer offset, @RequestParam(value="pageSize", required=false) Integer pageSize, @RequestParam(value="sortBy", required=false) String sortBy) {
        if (offset == null) offset = 0;
        if (pageSize == null) pageSize = 10;
        if (StringUtils.isEmpty(sortBy)) sortBy = "id";

        return libraryService.getBooksPage(PageRequest.of(offset, pageSize, Sort.by(sortBy)));
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") Integer id) {
        return this.libraryService.getBookById(id);
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
