package com.betrybe.alexandria;


import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.exception.BookNotFoundException;
import com.betrybe.alexandria.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class BookIntegrationTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MockMvc mockMvc;

    @Container
    public static MySQLContainer MYSQL_CONTAINER = new MySQLContainer("mysql:8.0.32")
            .withDatabaseName("ecommerce");

    @DynamicPropertySource
    public static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
    }


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
