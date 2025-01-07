package com.project.library.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.library.entity.Book;
import com.project.library.entity.User;
import com.project.library.repository.BooksRepository;
import com.project.library.repository.UsersRepository;

@Service
public class UsersService {
    
    final private UsersRepository usersRepository;
    final private BooksRepository booksRepository;

    public UsersService(final UsersRepository usersRepository, final BooksRepository booksRepository) {
        this.usersRepository = usersRepository;
        this.booksRepository = booksRepository;
    }

    public Page<User> getUsersPage(PageRequest pageRequest) {
        return this.usersRepository.findAll(pageRequest);
    }

    public User getUserByUsername(String username) throws ResponseStatusException {
        Optional<User> userOptional = this.usersRepository.findByUsername(username);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }

        return userOptional.get();
    }

    public User createUser(User user) throws ResponseStatusException {
        Optional<User> userOptional = this.usersRepository.findByUsername(user.getName());

        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Utente già registrato");
        }

        return this.usersRepository.save(user);
    }

    public User updateUser(Integer id, User u) throws ResponseStatusException {
        Optional<User> userOptional = this.usersRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }

        User user = userOptional.get();

        if (u.getName() != null) {
            Optional<User> userOptional2 = this.usersRepository.findByUsername(user.getName());

            if (userOptional2.isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome utente non disponibile");
            }

            user.setName(u.getName());
        }
        
        return this.usersRepository.save(user);
    }

    public User deleteUser(Integer id) throws ResponseStatusException {
        Optional<User> userOptional = this.usersRepository.findById(id);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }

        User user = userOptional.get();
        this.usersRepository.delete(user);
        return user;
    }

    public Book assignBook(Integer userId, Integer bookId) throws ResponseStatusException {
        Optional<User> userOptional = this.usersRepository.findById(userId);
        Optional<Book> bookOptional = this.booksRepository.findById(bookId);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }
        if (!bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato");
        }

        User user = userOptional.get();
        Book book = bookOptional.get();

        if (book.getIsAvailable() == false) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Libro non disponibile");
        }

        user.getBooksBorrowed().add(book);
        book.setIsAvailable(false);
        book.setUser(user);

        this.usersRepository.save(user);
        return this.booksRepository.save(book);
    }

    public Book returnBook(Integer userId, Integer bookId) throws ResponseStatusException {
        Optional<User> userOptional = this.usersRepository.findById(userId);
        Optional<Book> bookOptional = this.booksRepository.findById(bookId);

        if (!userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato");
        }
        if (!bookOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Libro non trovato");
        }

        User user = userOptional.get();
        Book book = bookOptional.get();

        user.getBooksBorrowed().remove(book);
        book.setIsAvailable(true);
        book.setUser(null);

        this.usersRepository.save(user);
        return this.booksRepository.save(book);
    }
}
