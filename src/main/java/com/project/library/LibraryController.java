package com.project.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LibraryController {

    final private LibraryRepository libraryRepository;

    public LibraryController(final LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @GetMapping("/books")
    public Iterable<Book> getAllBooks() {
        return this.libraryRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") Integer id) throws ResponseStatusException {
        Optional<Book> bookOptional = this.libraryRepository.findById(id);

        if (!bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }

        Book book = bookOptional.get();
        return book;
    }

    @GetMapping("/books") 
    public List<Book> searchBooks(@RequestParam(name="author", required=false) String author, @RequestParam(name="genre", required=false) String genre, @RequestParam(name="isAvailable", required=false) Boolean isAvailable) {
        if (author != null) {
            return this.libraryRepository.findByAuthor(author);
        } else if (genre != null) {
            return this.libraryRepository.findByGenre(genre);
        } else if (isAvailable != null && isAvailable == true) {
            return this.libraryRepository.findByIsAvailableTrue();
        } else {
            return new ArrayList<>();
        }
    }

    @PostMapping("/books/addBook")
    public Book createBook(@RequestBody Book book) throws ResponseStatusException {
        Optional<Book> b = this.libraryRepository.findById(book.getId());

        if (b.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book already exists");
        }

        return this.libraryRepository.save(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable("id") Integer id, @RequestBody Book b) {
        Optional<Book> bookOptional = this.libraryRepository.findById(id);

        if (!bookOptional.isPresent()) {
            return null;
        }

        Book book = bookOptional.get();

        if (b.getTitle() != null) {
            book.setTitle(b.getTitle());
        }
        if (b.getAuthor() != null) {
            book.setAuthor(b.getAuthor());
        }
        if (b.getPublishedYear() != null) {
            book.setPublishedYear(b.getPublishedYear());
        }
        if (b.getGenre() != null) {
            book.setGenre(b.getGenre());
        }
        if (b.getIsAvailable() != null) {
            book.setIsAvailable(b.getIsAvailable());
        }

        return this.libraryRepository.save(book);
    }

    @DeleteMapping("book/{id}")
    public Book removeBook(@PathVariable("id") Integer id) throws ResponseStatusException {
        Optional<Book> bookOptional = this.libraryRepository.findById(id);

        if (!bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }

        Book book = bookOptional.get();
        this.libraryRepository.delete(book);
        return book;
    }
}
