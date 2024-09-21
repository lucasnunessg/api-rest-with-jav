package com.betrybe.alexandria.controller.dto;

import java.util.List;

import com.betrybe.alexandria.entity.Book;

public record BookDto(Long id, String title, String genre, PublisherDto publisher, List<AuthorDto> authors) {
    public static BookDto fromEntity(Book book) {
        PublisherDto publisherDto = book.getPublisher() != null ?
                PublisherDto.fromEntity(book.getPublisher()) : null;

        return new BookDto(
                book.getId(),
                book.getGenre(),
                book.getGenre(),
                publisherDto,
                book.getAuthors()
                        .stream()
                        .map(AuthorDto::fromEntity)
                        .toList()
        );
    }
}
