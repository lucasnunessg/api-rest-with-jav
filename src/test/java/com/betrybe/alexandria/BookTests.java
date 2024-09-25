package com.betrybe.alexandria;

import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BookTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    public void testBooksRepository() {
        Book book = new Book();
        book.setTitle("Lucas em Java");
        book.setGenre("Histórias do Lucas em Java");

        Book savedBook = bookRepository.save(book);


        assertNotNull(savedBook.getId());
        assertEquals(book.getTitle(), savedBook.getTitle());
        assertEquals(book.getGenre(), savedBook.getGenre());

    }
}
