package com.example.bookservice.service;

import com.example.bookservice.LibraryApi.LibraryApi;
import com.example.bookservice.exception.BookNotFoundException;
import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> allBooks = (List<Book>) bookRepository.findAll();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }

    public ResponseEntity<Book> getBookById(Long id) throws BookNotFoundException {
        Optional<Book> opt_book = bookRepository.findById(id);
        if(opt_book.isPresent()){
            return
                    new ResponseEntity<>(opt_book.get(),HttpStatus.OK);
        }
        else throw new BookNotFoundException("book with id '"+id+"' not found");


    }

    public ResponseEntity<Book> getBookByISBN(String isbn) throws BookNotFoundException {
        Optional<Book> opt_book = bookRepository.getBookByIsbn(isbn);
        if(opt_book.isPresent()){
            return
                    new ResponseEntity<>(opt_book.get(),HttpStatus.OK);
        }
        else throw new BookNotFoundException("book with isbn '"+isbn+"' not found");

    }

    public ResponseEntity<Book> addBook(Book book,String token) throws IOException {
        bookRepository.save(book);
        LibraryApi.addBookInLibrary(book.getId(),token);
        System.out.println(token);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }

    public ResponseEntity<Book> updateBook(Long id, Book book) throws BookNotFoundException {
        Optional<Book> opt_book = bookRepository.findById(id);
        if(opt_book.isPresent()){
            opt_book.get().setAuthor(book.getAuthor());
            opt_book.get().setName(book.getName());
            opt_book.get().setGenre(book.getGenre());
            opt_book.get().setIsbn(book.getIsbn());
            opt_book.get().setDescription(book.getDescription());
            bookRepository.save(opt_book.get());

            return
                    new ResponseEntity<>(opt_book.get(),HttpStatus.OK);
        }
        else throw new BookNotFoundException("book with id '"+id+"' not found");

    }

    public HttpStatus deleteBookById(Long id) throws BookNotFoundException {
        Optional<Book> opt_book = bookRepository.findById(id);
        if(opt_book.isPresent()){
            bookRepository.delete(opt_book.get());
            return HttpStatus.OK;
        }
        else throw new BookNotFoundException("book with id '"+id+"' not found");
    }
}
