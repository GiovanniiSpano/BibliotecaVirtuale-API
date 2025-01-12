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

import com.project.library.entity.Book;
import com.project.library.exception.RedirectException;
import com.project.library.service.BooksService;

import io.micrometer.common.util.StringUtils;

@RestController
public class BooksController {

    final private BooksService booksService;

    public BooksController(final BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/")
    public void redirectToBooks() throws RedirectException {
        throw new RedirectException("/books");
    }

    @GetMapping("/books")
    public Page<Book> getAllBooks(@RequestParam(value="offset", required=false) Integer offset, @RequestParam(value="pageSize", required=false) Integer pageSize, @RequestParam(value="sortBy", required=false) String sortBy) {
        if (offset == null) offset = 0;
        if (pageSize == null) pageSize = 10;
        if (StringUtils.isEmpty(sortBy)) sortBy = "id";

        return this.booksService.getBooksPage(PageRequest.of(offset, pageSize, Sort.by(sortBy)));
    }

    @GetMapping("/books/search")
    public Page<Book> searchBooks(@RequestParam(value="offset", required=false) Integer offset, @RequestParam(value="pageSize", required=false) Integer pageSize, @RequestParam(value="sortBy", required=false) String sortBy, @RequestParam(value="author", required=false) String author, @RequestParam(value="genre", required=false) String genre, @RequestParam(value="isAvailable", required=false) boolean isAvailable) throws RedirectException {
        if (offset == null) offset = 0;
        if (pageSize == null) pageSize = 10;
        if (StringUtils.isEmpty(sortBy)) sortBy = "id";

        if (!StringUtils.isEmpty(author) && StringUtils.isEmpty(genre) && !isAvailable) {
            return this.booksService.getBooksPageByAuthor(PageRequest.of(offset, pageSize, Sort.by(sortBy)), author);
        } else if (!StringUtils.isEmpty(author) && !StringUtils.isEmpty(genre) && !isAvailable) {
            return this.booksService.getBooksPageByAuthorAndGenre(PageRequest.of(offset, pageSize, Sort.by(sortBy)), author, genre);
        } else if (!StringUtils.isEmpty(author) && StringUtils.isEmpty(genre) && isAvailable) {
            return this.booksService.getBooksPageByAuthorAndIsAvailableTrue(PageRequest.of(offset, pageSize, Sort.by(sortBy)), author);
        } else if (StringUtils.isEmpty(author) && !StringUtils.isEmpty(genre) && isAvailable) {
            return this.booksService.getBooksPageByGenreAndIsAvailableTrue(PageRequest.of(offset, pageSize, Sort.by(sortBy)), genre);
        } else if (StringUtils.isEmpty(author) && !StringUtils.isEmpty(genre) && !isAvailable) {
            return this.booksService.getBooksPageByGenre(PageRequest.of(offset, pageSize, Sort.by(sortBy)), genre);
        } else if (StringUtils.isEmpty(author) && StringUtils.isEmpty(genre) && isAvailable) {
            return this.booksService.getBooksPageByIsAvailableTrue(PageRequest.of(offset, pageSize, Sort.by(sortBy)));
        } else {
            throw new RedirectException("/books");
        }
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") Integer id) {
        return this.booksService.getBookById(id);
    }

    @PostMapping("/books/addBook")
    public Book createBook(@RequestBody Book book) {
        return this.booksService.createBook(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book book) {
        return this.booksService.updateBook(id, book);
    }

    @DeleteMapping("/books/{id}")
    public Book removeBook(@PathVariable("id") Integer id) {
        return this.booksService.removeBook(id);
    }
}
