package com.betrybe.alexandria.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books_details")
public class BookDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String summary;
    private Integer page_count;
    private Integer year;
    private String isbn;
    private Integer book_id;

    public BookDetails() {

    }

    public BookDetails(String summary, Integer page_count, Integer year, String isbn, Integer book_id) {
        this.summary = summary;
        this.page_count = page_count;
        this.year = year;
        this.isbn = isbn;
        this.book_id = book_id;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getPage_count() {
        return page_count;
    }

    public void setPage_count(Integer page_count) {
        this.page_count = page_count;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
