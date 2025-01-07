package com.project.library.service;

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
    final private BooksRepository libraryRepository;

    public BooksService(final BooksRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Page<Book> getBooksPage(PageRequest pageRequest) {
        return libraryRepository.findAll(pageRequest);
    }

    public Page<Book> getBooksPageByAuthor(PageRequest pageRequest, String author) {
        return libraryRepository.findByAuthor(pageRequest, author);
    }

    public Page<Book> getBooksPageByGenre(PageRequest pageRequest, String genre) {
        return libraryRepository.findByGenre(pageRequest, genre);
    }

    public Page<Book> getBooksPageByIsAvailableTrue(PageRequest pageRequest) {
        return libraryRepository.findByIsAvailableTrue(pageRequest);
    }

    public Book getBookById(Integer id) throws ResponseStatusException {
        Optional<Book> bookOptional = this.libraryRepository.findById(id);

        if (!bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato");
        }

        return bookOptional.get();
    }

    public Book createBook(Book book) throws ResponseStatusException {
        Optional<Book> bookOptional = this.libraryRepository.findByTitle(book.getTitle());

        if (bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Il libro è già presente");
        }

        return this.libraryRepository.save(book);
    }

    public Book updateBook(Integer id, Book b) throws ResponseStatusException {
        Optional<Book> bookOptional = this.libraryRepository.findById(id);

        if (!bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato");
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato");
        }

        Book book = bookOptional.get();
        this.libraryRepository.delete(book);
        return book;
    }
}
