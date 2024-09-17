package com.betrybe.alexandria.controller;


import com.betrybe.alexandria.controller.dto.BookCreationDto;
import com.betrybe.alexandria.controller.dto.BookDto;
import com.betrybe.alexandria.entity.Book;
import java.util.List;
import com.betrybe.alexandria.exception.BookNotFoundException;
import com.betrybe.alexandria.service.BookService;
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
       List<Book> allBooks = bookService.findAll();
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

}
