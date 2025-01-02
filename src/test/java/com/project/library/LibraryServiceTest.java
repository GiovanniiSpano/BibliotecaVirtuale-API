package com.project.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

import com.project.library.entity.Book;
import com.project.library.repository.LibraryRepository;
import com.project.library.service.LibraryService;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceTest {

    @Mock
    private LibraryRepository libraryRepository;

    @InjectMocks
    private LibraryService libraryService;

    @Test
    public void getBooksFromRepo() {
        List<Book> data = Arrays.asList(
            new Book("1984", "George Orwell", 1949, "Distopico", true),
            new Book("Il nome della rosa", "Umberto Eco", 1980, "Romanzo", false),
            new Book("Il signore degli anelli", "J.R.R Tolkien", 1954, "Fantasy", true)
        );

        when(libraryRepository.findAll()).thenReturn(data);

        Iterable<Book> result = libraryService.getAllBooks();

        assertEquals(data, result);
    }

    @Test
    public void createBookTest() {
        Book book = new Book("Il signore degli anelli", "J.R.R Tolkien", 1954, "Fantasy", true);

        when(libraryRepository.save(book)).thenReturn(book);

        Book result = libraryService.createBook(book);

        assertEquals(book, result);
    }

    @Test
    public void searchBooksTest() {
        List<Book> data = Arrays.asList(
            new Book("1984", "George Orwell", 1949, "Distopico", true),
            new Book("Il nome della rosa", "Umberto Eco", 1980, "Romanzo", false),
            new Book("Il signore degli anelli", "J.R.R Tolkien", 1954, "Fantasy", true)
        );

        List<Book> dataAuthor = Arrays.asList(data.get(0));
        List<Book> dataGenre = Arrays.asList(data.get(1));
        List<Book> dataIsAvailable = Arrays.asList(data.get(0), data.get(2));

        when(libraryRepository.findByAuthor("George Orwell")).thenReturn(dataAuthor);
        when(libraryRepository.findByGenre("Romanzo")).thenReturn(dataGenre);
        when(libraryRepository.findByIsAvailableTrue()).thenReturn(dataIsAvailable);

        List<Book> resultAuthor = libraryService.searchBooks("George Orwell", null, null);
        List<Book> resultGenre = libraryService.searchBooks(null, "Romanzo", null);
        List<Book> resultIsAvailable = libraryService.searchBooks(null, null, true);
        List<Book> resultNull = libraryService.searchBooks(null, null, null);

        assertEquals(dataAuthor, resultAuthor);
        assertEquals(dataGenre, resultGenre);
        assertEquals(dataIsAvailable, resultIsAvailable);
        assertEquals(new ArrayList<>(), resultNull);
    }
}
