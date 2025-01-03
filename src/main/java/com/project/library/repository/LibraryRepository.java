package com.project.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.library.entity.Book;

public interface LibraryRepository extends JpaRepository<Book, Integer> {

}
