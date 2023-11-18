package com.example.bookservice.controller;

import com.example.bookservice.exception.BookNotFoundException;
import com.example.bookservice.model.Book;
import com.example.bookservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.DELETE;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping("/all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) throws BookNotFoundException {
        return bookService.getBookById(id);
    }
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<Book> getBookByISBN(@PathVariable String isbn) throws BookNotFoundException {
        return bookService.getBookByISBN(isbn);
    }
    @PostMapping("/add-book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) throws IOException {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Book> updateBook(@PathVariable Long id,@RequestBody Book book) throws BookNotFoundException {
        return bookService.updateBook(id,book);
    }
    @DeleteMapping("{id}/delete")
    public HttpStatus deleteBook(@PathVariable Long id) throws BookNotFoundException {
        return bookService.deleteBookById(id);
    }

}
