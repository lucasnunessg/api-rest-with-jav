package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Author;
import com.betrybe.alexandria.entity.Book;
import com.betrybe.alexandria.entity.BookDetail;
import com.betrybe.alexandria.entity.Publisher;
import com.betrybe.alexandria.exception.AuthorNotFoundException;
import com.betrybe.alexandria.exception.BookDetailNotFoundException;
import com.betrybe.alexandria.exception.BookNotFoundException;
import com.betrybe.alexandria.exception.PublisherNotFoundException;
import com.betrybe.alexandria.repository.BookDetailRepository;
import com.betrybe.alexandria.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookDetailRepository bookDetailRepository;
    private final PublisherService publisherService;
    private final AuthorService authorService;

    @Autowired
    public BookService(BookRepository bookRepository, BookDetailRepository bookDetailRepository, PublisherService publisherService, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.bookDetailRepository = bookDetailRepository;
        this.publisherService = publisherService;
        this.authorService = authorService;
    }


    public Book findById(Long id) throws BookNotFoundException {
        return bookRepository.findById((id))
                .orElseThrow(BookNotFoundException::new);
    }

    public List<Book> findAllBook() {
    return bookRepository.findAll();
    }



    public Book create(Book book) {
        return bookRepository.save(book);
    }
    //aqui recebe o id do livro  //a entidade com os novos dados a serem alterados
    public Book update(Long id, Book book) throws BookNotFoundException {
        Book bookFromDb = findById(id);

        bookFromDb.setTitle(book.getTitle());
        bookFromDb.setGenre(book.getTitle());

        return bookRepository.save(bookFromDb);
    }

    public Book deleteById(Long id) throws BookNotFoundException {
        Book book = findById(id);


        bookRepository.deleteById(id);
        return book;
    }

    public BookDetail createBookDetail(Long bookId, BookDetail bookDetail) throws BookNotFoundException {
        Book book = findById(bookId); //O hibernate vai olhar pro DONO do relacionamento pra saber o q precisa salvar

        bookDetail.setBook(book); //aqui é a associação

        return bookDetailRepository.save(bookDetail);
    }

    public BookDetail getBookDetail(Long bookId) throws BookNotFoundException, BookDetailNotFoundException {
        Book book = findById(bookId);

        BookDetail bookDetail = book.getDetail();

        if (bookDetail == null) {
            throw new BookDetailNotFoundException();
        }
        return bookDetail;
    }

    public BookDetail updateBookDetail(Long bookId, BookDetail bookDetail) throws BookDetailNotFoundException, BookNotFoundException {
        BookDetail bookDetailFromDb = getBookDetail(bookId);

        bookDetailFromDb.setSummary(bookDetail.getSummary());
        bookDetailFromDb.setPageCount(bookDetail.getPageCount());
        bookDetailFromDb.setIsbn(bookDetail.getIsbn());
        bookDetailFromDb.setYear(bookDetail.getYear());

        if (bookDetail == null) {
            throw new BookDetailNotFoundException();
        }

        return bookDetailRepository.save(bookDetailFromDb);
        }

        public BookDetail removeBookDetail(Long bookId) throws BookNotFoundException, BookDetailNotFoundException {
        Book book = findById(bookId);
        BookDetail bookDetail = book.getDetail();

        if (bookDetail == null) {
            throw new BookDetailNotFoundException();
        }

        book.setDetail(null);
        bookDetail.setBook(null); //aqui tem q fazer desse jeito pq precisa quebrar o relacionamento entre book-book detail pra poder apagar

        return bookDetail;

        }

        public Book setBookPublisher(Long bookId, Long publisherId) throws BookNotFoundException, PublisherNotFoundException {
            Book book = findById(bookId);
            Publisher publisher = publisherService.findById(publisherId);

                    book.setPublisher(publisher);

            return bookRepository.save(book);
        }

        public Book removeBookPublisher(Long bookId) throws BookNotFoundException {
        Book book = findById(bookId);

        book.setPublisher(null); //nao precisa quebrar o relacionamento dos dois lados pq esta trabalhando so com o livro

        return bookRepository.save(book);
        }

        public Book addBookAuthor(Long bookId, Long authorId) throws BookNotFoundException, AuthorNotFoundException {
        Book book = findById(bookId);
        Author author = authorService.findById(authorId);

        book.getAuthors().add(author); //lá no relacionamento, é uma lista , por isso o .add

        return bookRepository.save(book);
        }

    public Book removeBookAuthor(Long bookId, Long authorId) throws BookNotFoundException, AuthorNotFoundException {
        Book book = findById(bookId);
        Author author = authorService.findById(authorId);

        book.getAuthors().remove(author); //lá no relacionamento, é uma lista , por isso o .remove

        return bookRepository.save(book);
    }


    }


