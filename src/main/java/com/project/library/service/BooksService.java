package com.project.library.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.library.entity.Book;
import com.project.library.repository.BooksRepository;

@Service
public class BooksService {
    final private BooksRepository booksRepository;

    public BooksService(final BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Page<Book> getBooksPage(PageRequest pageRequest) {
        return booksRepository.findAll(pageRequest);
    }

    public Page<Book> getBooksPageByAuthor(PageRequest pageRequest, String author) {
        return booksRepository.findAllByAuthor(pageRequest, author);
    }

    public Page<Book> getBooksPageByAuthorAndGenre(PageRequest pageRequest, String author, String genre) {
        return booksRepository.findAllByAuthorAndGenre(pageRequest, author, genre);
    }

    public Page<Book> getBooksPageByAuthorAndIsAvailableTrue(PageRequest pageRequest, String author) {
        return booksRepository.findAllByAuthorAndIsAvailableTrue(pageRequest, author);
    }

    public Page<Book> getBooksPageByGenreAndIsAvailableTrue(PageRequest pageRequest, String genre) {
        return booksRepository.findAllByGenreAndIsAvailableTrue(pageRequest, genre);
    }

    public Page<Book> getBooksPageByGenre(PageRequest pageRequest, String genre) {
        return booksRepository.findAllByGenre(pageRequest, genre);
    }

    public Page<Book> getBooksPageByIsAvailableTrue(PageRequest pageRequest) {
        return booksRepository.findAllByIsAvailableTrue(pageRequest);
    }

    public Book getBookById(Integer id) throws ResponseStatusException {
        Book book = this.booksRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato"));

        return book;
    }

    public Book createBook(Book book) {
        Optional<Book> bookOptional = this.booksRepository.findByTitle(book.getTitle());

        if (bookOptional.isPresent()) {
            Book b = bookOptional.get();

            if (b.getAuthor().equals(book.getAuthor()) && b.getGenre().equals(book.getGenre()) && Objects.equals(b.getPublishedYear(), book.getPublishedYear())) {
                b.setNumAvailable(b.getNumAvailable() + 1);
                return this.booksRepository.save(b);
            }
        }

        return this.booksRepository.save(book);
    }

    public Book updateBook(Integer id, Book b) throws ResponseStatusException {
        Book book = this.booksRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato"));

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

        return this.booksRepository.save(book);
    }

    public Book removeBook(Integer id) throws ResponseStatusException {
        Book book = this.booksRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato"));

        this.booksRepository.delete(book);
        return book;
    }
}
