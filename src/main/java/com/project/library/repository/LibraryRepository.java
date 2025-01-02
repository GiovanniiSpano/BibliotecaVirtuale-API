package com.project.library.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.library.entity.Book;

public interface LibraryRepository extends CrudRepository<Book, Integer> {
    public List<Book> findByAuthor(String author);
    public List<Book> findByGenre(String genre);
    public List<Book> findByIsAvailableTrue();
}
