package com.project.library.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.library.entity.Book;
import com.project.library.repository.LibraryRepository;

@Service
public class LibraryService {
    final private LibraryRepository libraryRepository;

    public LibraryService(final LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Page<Book> getBooksPage(PageRequest pageRequest) {
        return libraryRepository.findAll(pageRequest);
    }

    public Book getBookById(Integer id) throws ResponseStatusException {
        Optional<Book> bookOptional = this.libraryRepository.findById(id);

        if (!bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }

        Book book = bookOptional.get();
        return book;
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
