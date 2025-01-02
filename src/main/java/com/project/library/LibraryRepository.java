package com.project.library;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface LibraryRepository extends CrudRepository<Book, Integer> {
    public List<Book> findByAuthor(String author);
    public List<Book> findByGenre(String genre);
    public List<Book> findByIsAvailableTrue();
}
