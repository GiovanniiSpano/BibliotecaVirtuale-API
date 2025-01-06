package com.project.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.library.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    
}
