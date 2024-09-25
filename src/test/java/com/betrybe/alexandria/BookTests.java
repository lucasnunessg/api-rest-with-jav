package com.betrybe.alexandria;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.exception.BookNotFoundException;
import com.betrybe.alexandria.repository.BookRepository;
import com.betrybe.alexandria.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
@ActiveProfiles("test")
public class BookTests {

    @Autowired
    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    @Test
    public void testBooksRepository() {
        Book book = new Book();
        book.setTitle("Lucas em Java");
        book.setGenre("Histórias do Lucas em Java");

        Book bookToReturn = new Book();
        bookToReturn.setId(123L);
        bookToReturn.setTitle(book.getTitle());
        bookToReturn.setGenre(book.getGenre());

        Mockito.when(bookRepository.save(any()))
                .thenReturn(bookToReturn);

        Book savedBook = bookRepository.save(book);

        Mockito.verify(bookRepository).save(any());

        assertEquals(123L, savedBook.getId());
        assertEquals(book.getTitle(), savedBook.getTitle());
        assertEquals(book.getGenre(), savedBook.getGenre());

    }

    @Test
    public void testBookRetrieval() throws BookNotFoundException {
        Book book = new Book();
        book.setId(255L);
        book.setTitle("Lucas em Js");
        book.setGenre("Histórias do Lucas em Js");

        Mockito.when(bookRepository.findById(eq(255L)))
                .thenReturn(Optional.of(book));

        Book returnedBook = bookService.findById(255L);

        Mockito.verify(bookRepository).findById(eq(255L));

        assertEquals(returnedBook.getId(), book.getId());
        assertEquals(returnedBook.getTitle(), book.getTitle());
        assertEquals(returnedBook.getGenre(), book.getGenre());


    }


    @Test
    public void testWithException() {
        Mockito.when(bookRepository.findById(any()))
                .thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.findById(151L));

        Mockito.verify(bookRepository).findById(eq(151L));
    }


}
