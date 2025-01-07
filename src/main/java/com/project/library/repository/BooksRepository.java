package com.project.library.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.library.entity.Book;

public interface BooksRepository extends JpaRepository<Book, Integer> {
    public Optional<Book> findByTitle(String title);
    public Page<Book> findByAuthor(PageRequest pageRequest, String author);
    public Page<Book> findByGenre(PageRequest pageRequest, String genre);
    public Page<Book> findByIsAvailableTrue(PageRequest pageRequest);
}
