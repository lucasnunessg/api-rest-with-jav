package com.betrybe.alexandria;


import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.exception.BookNotFoundException;
import com.betrybe.alexandria.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class BookIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testBookCreation() throws Exception {
        Book book = new Book();

        book.setTitle("The last of us");
        book.setGenre("Suspense");

        Book savedBook = bookRepository.save(book);

        System.out.println("Title: " + book.getTitle());
        System.out.println("Genre: " + book.getGenre());


        String bookUrl = "/books/%s".formatted(savedBook.getId());
        mockMvc.perform(get(bookUrl)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.title").value("The last of us"))
                .andExpect(jsonPath("$.genre").value("Suspense"));

    }


}
