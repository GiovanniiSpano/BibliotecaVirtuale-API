package com.project.library;

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
    public void createBookTest() {
        Book book = new Book("Il signore degli anelli", "J.R.R Tolkien", 1954, "Fantasy", true);

        when(libraryRepository.save(book)).thenReturn(book);

        Book result = libraryService.createBook(book);

        assertEquals(book, result);
    }

}
