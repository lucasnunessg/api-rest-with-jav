package com.betrybe.alexandria.controller;

import com.betrybe.alexandria.controller.dto.AuthorCreationDto;
import com.betrybe.alexandria.controller.dto.AuthorDto;
import com.betrybe.alexandria.entity.Author;
import com.betrybe.alexandria.exception.AuthorNotFoundException;
import com.betrybe.alexandria.service.AuthorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable Long id) throws AuthorNotFoundException {
        return AuthorDto.fromEntity(authorService.findById(id));

    }

    public List<AuthorDto> getAllAuthors() {
        List<Author> allAuthors = authorService.findAll();

      return allAuthors.stream()
                .map(AuthorDto::fromEntity)
                .toList();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDto createAuthor(@RequestBody AuthorCreationDto authorCreationDto) {
        return AuthorDto.fromEntity(authorService.create(authorCreationDto.toEntity()));
    }

    @PutMapping("/{id}")
    public AuthorDto updateBook(@PathVariable Long id, @RequestBody AuthorCreationDto authorCreationDto) throws AuthorNotFoundException{
        return AuthorDto.fromEntity(authorService.update(id, authorCreationDto.toEntity()));
    }

    @DeleteMapping("/{id}")
    public AuthorDto deleteAuthor(@PathVariable Long id) throws AuthorNotFoundException {
        return AuthorDto.fromEntity(authorService.deleteById(id));
    }



}
