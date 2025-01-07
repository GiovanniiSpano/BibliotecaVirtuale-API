package com.project.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.library.entity.User;

public interface UsersRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
}
