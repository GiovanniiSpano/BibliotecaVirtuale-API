package com.project.library.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.library.entity.Book;
import com.project.library.entity.User;
import com.project.library.repository.BooksRepository;
import com.project.library.repository.UsersRepository;

@Service
public class LibraryService {
    final private UsersRepository usersRepository;
    final private BooksRepository booksRepository;


    public LibraryService(UsersRepository usersRepository, BooksRepository booksRepository) {
        this.usersRepository = usersRepository;
        this.booksRepository = booksRepository;
    }

    public Book assignBook(Integer userId, Integer bookId) throws ResponseStatusException {
        User user = this.usersRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));

        Book book = this.booksRepository.findById(bookId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato"));

        if (book.getIsAvailable() == false) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Libro non disponibile");
        }

        user.getBooksBorrowed().add(book);
        book.setNumAvailable(book.getNumAvailable() - 1);
        book.setUser(user);

        this.usersRepository.save(user);
        return this.booksRepository.save(book);
    }

    public Book returnBook(Integer userId, Integer bookId) throws ResponseStatusException {
        User user = this.usersRepository.findById(userId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));

        Book book = this.booksRepository.findById(bookId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato"));

        user.getBooksBorrowed().remove(book);
        book.setNumAvailable(book.getNumAvailable() + 1);
        book.setUser(null);

        this.usersRepository.save(user);
        return this.booksRepository.save(book);
    }
}
