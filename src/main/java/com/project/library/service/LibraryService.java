package com.project.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.project.library.entity.Book;
import com.project.library.repository.LibraryRepository;

public class LibraryService {
    final private LibraryRepository libraryRepository;

    public LibraryService(final LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Iterable<Book> getAllBooks() {
        return this.libraryRepository.findAll();
    }

    public Book getBookById(Integer id) throws ResponseStatusException {
        Optional<Book> bookOptional = this.libraryRepository.findById(id);

        if (!bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }

        Book book = bookOptional.get();
        return book;
    }
    
    public List<Book> searchBooks(String author, String genre, Boolean isAvailable) {
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

    public Book createBook(Book book) throws ResponseStatusException {
        Optional<Book> b = this.libraryRepository.findById(book.getId());

        if (b.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book already exists");
        }

        return this.libraryRepository.save(book);
    }

    public Book updateBook(Integer id, Book b) {
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

    public Book removeBook(Integer id) throws ResponseStatusException {
        Optional<Book> bookOptional = this.libraryRepository.findById(id);

        if (!bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }

        Book book = bookOptional.get();
        this.libraryRepository.delete(book);
        return book;
    }
}
