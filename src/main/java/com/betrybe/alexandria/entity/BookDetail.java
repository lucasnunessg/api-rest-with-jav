package com.betrybe.alexandria.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "books_details")
public class BookDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String summary;
    private Integer pageCount;
    private Integer year;
    private String isbn;
    //escolhi o bookdetail pra ser o dono do relcaionamento
    @OneToOne(optional = false) //aqui é pra dizer q nao pode existir o book detail sem existir um livro
    @JoinColumn(name = "book_id") //aqui é definido a foreign key
    private Book book;

    public BookDetail(String summary, Integer pageCount, String year, String isbn) {

    }

    public BookDetail(String summary, Integer page_count, Integer year, String isbn) {
        this.summary = summary;
        this.pageCount = page_count;
        this.year = year;
        this.isbn = isbn;

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

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
