package com.example.bookservice.controller;

import com.example.bookservice.dto.BookDTO;
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
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return bookService.getAllBooks();
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) throws BookNotFoundException {
        return bookService.getBookById(id);
    }
    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookDTO> getBookByISBN(@PathVariable String isbn) throws BookNotFoundException {
        return bookService.getBookByISBN(isbn);
    }
    @PostMapping("/add-book")
    public ResponseEntity<BookDTO> addBook(@RequestBody Book book,@RequestHeader("Authorization") String token) throws IOException {
        return bookService.addBook(book,token);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,@RequestBody Book book) throws BookNotFoundException {
        return bookService.updateBook(id,book);
    }
    @DeleteMapping("{id}/delete")
    public HttpStatus deleteBook(@PathVariable Long id) throws BookNotFoundException {
        return bookService.deleteBookById(id);
    }

}
