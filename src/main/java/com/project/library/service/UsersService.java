package com.project.library.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.library.entity.User;
import com.project.library.repository.UsersRepository;

@Service
public class UsersService {
    
    final private UsersRepository usersRepository;

    public UsersService(final UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Page<User> getUsersPage(PageRequest pageRequest) {
        return this.usersRepository.findAll(pageRequest);
    }

    public User getUserByUsername(String username) throws ResponseStatusException {
        User user = this.usersRepository.findByUsername(username)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));

        return user;
    }

    public User createUser(User user) throws ResponseStatusException {
        Optional<User> userOptional = this.usersRepository.findByUsername(user.getName());

        if (userOptional.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Utente già registrato");
        }

        return this.usersRepository.save(user);
    }

    public User updateUser(Integer id, User u) throws ResponseStatusException {
        User user = this.usersRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));

        if (u.getName() != null) {
            Optional<User> userOptional = this.usersRepository.findByUsername(user.getName());

            if (userOptional.isPresent()) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Nome utente non disponibile");
            }

            user.setName(u.getName());
        }
        
        return this.usersRepository.save(user);
    }

    public User deleteUser(Integer id) throws ResponseStatusException {
        User user = this.usersRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato"));

        this.usersRepository.delete(user);
        return user;
    }
}
