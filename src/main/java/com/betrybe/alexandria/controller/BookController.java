package com.betrybe.alexandria.controller;


import com.betrybe.alexandria.controller.dto.*;
import com.betrybe.alexandria.entity.Book;
import java.util.List;

import com.betrybe.alexandria.exception.AuthorNotFoundException;
import com.betrybe.alexandria.exception.BookDetailNotFoundException;
import com.betrybe.alexandria.exception.BookNotFoundException;
import com.betrybe.alexandria.exception.PublisherNotFoundException;
import com.betrybe.alexandria.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) throws BookNotFoundException {
        return BookDto.fromEntity(bookService.findById(id));
    }

    public List<BookDto> getAllBooks() {
       List<Book> allBooks = bookService.findAllBook();
       return allBooks.stream()
               .map(BookDto::fromEntity)
               .toList();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto createBook(@RequestBody BookCreationDto bookCreationDto) {
        return BookDto.fromEntity(bookService.create(bookCreationDto.toEntity())
        );
    }
    @PutMapping("/{id}")
   public BookDto updateBook(@PathVariable Long id, @RequestBody BookCreationDto bookCreationDto) throws BookNotFoundException {
        return BookDto.fromEntity(bookService.update(id, bookCreationDto.toEntity()));
    }

    @DeleteMapping("/{id}")
    public BookDto deleteBook(@PathVariable Long id) throws BookNotFoundException {
        return BookDto.fromEntity(bookService.deleteById(id));
    }

    @PostMapping("/{bookId}/detail")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDetailDto createBookDetail(@PathVariable Long bookId, @RequestBody BookDetailCreationDto bookDetailCreationDto) throws BookNotFoundException {
        return BookDetailDto.fromEntity(
                bookService.createBookDetail(bookId, bookDetailCreationDto.toEntity())
        );
      //  bookService.createBookDetail(bookId, bookDetailCreationDto.toEntity()) assim retornaria uma entidade, entao é preciso converter.
    }

    @GetMapping("/{bookId}/detail")
    public BookDetailDto getBookDetail(@PathVariable Long bookId) throws BookDetailNotFoundException, BookNotFoundException {
        return BookDetailDto.fromEntity(bookService.getBookDetail(bookId));
    }

    @PutMapping("/{bookId}/detail")
    public BookDetailDto updateBookDetail(@PathVariable Long bookId, @RequestBody BookDetailCreationDto bookDetailCreationDto) throws BookDetailNotFoundException, BookNotFoundException {
        return BookDetailDto.fromEntity(bookService.updateBookDetail(bookId, bookDetailCreationDto.toEntity()));
    }

    @DeleteMapping("/{bookId}/detail")
    public BookDetailDto deleteBookDetail(@PathVariable Long bookId) throws BookNotFoundException, BookDetailNotFoundException {
        return BookDetailDto.fromEntity(bookService.removeBookDetail(bookId));
    }

    @PutMapping("/{bookId}/publisher/{publisherId}")
    public BookDto setBookPublisher(@PathVariable Long bookId, Long publisherId) throws BookNotFoundException, PublisherNotFoundException {
        return BookDto.fromEntity(bookService.setBookPublisher(bookId, publisherId));

    }

    @DeleteMapping("/{bookId}/publisher")
    public BookDto deleteBookPublisher(@PathVariable Long bookId) throws BookNotFoundException {
        return BookDto.fromEntity(
                bookService.removeBookPublisher(bookId)
        );
    }

    @PutMapping("/{bookId}/authors/authorsId")
    public BookDto addBookAuthor(@PathVariable Long bookId, @PathVariable Long authorId) throws AuthorNotFoundException, BookNotFoundException {
        return BookDto.fromEntity(bookService.addBookAuthor(bookId, authorId)); //essa rota cria a associação
    }

    @DeleteMapping("/{bookId}/authors/{authorId}")
    public BookDto removeFrmoAuthor(@PathVariable Long bookId, @PathVariable Long authorId) throws AuthorNotFoundException, BookNotFoundException {
        return BookDto.fromEntity(bookService.removeBookAuthor(bookId, authorId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreationDto createUser(@Valid @RequestBody UserCreationDto userCreate) {
        // aqui os metodos, deveria ser criado também um service e um from entity no dto
        return null;
    }
}
