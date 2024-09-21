package com.betrybe.alexandria.exception;

public class BookDetailNotFoundException extends Exception{
    public BookDetailNotFoundException() {
        super("Detalhes de livro não encontrados!");
    }
}
