package com.example.bookservice.service;

import com.example.bookservice.LibraryApi.LibraryApi;
import com.example.bookservice.dto.BookDTO;
import com.example.bookservice.dto.ModelMapperConfig;
import com.example.bookservice.exception.BookNotFoundException;
import com.example.bookservice.model.Book;
import com.example.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapperConfig modelMapper;
    @Autowired
    public BookService(BookRepository bookRepository, ModelMapperConfig modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> allBooks = (List<Book>) bookRepository.findAll();
        List<BookDTO> bookDTOs = allBooks.stream()
                .map(book -> modelMapper.modelMapper().map(book, BookDTO.class))
                .collect(Collectors.toList());

        return
                new ResponseEntity<>(bookDTOs, HttpStatus.OK);

    }

    public ResponseEntity<BookDTO> getBookById(Long id) throws BookNotFoundException {
        Optional<Book> opt_book = bookRepository.findById(id);
        if(opt_book.isPresent()){
            BookDTO bookDTO = modelMapper.modelMapper().map(opt_book.get(), BookDTO.class);
            return
                    new ResponseEntity<>(bookDTO,HttpStatus.OK);
        }
        else throw new BookNotFoundException("book with id '"+id+"' not found");

    }

    public ResponseEntity<BookDTO> getBookByISBN(String isbn) throws BookNotFoundException {
        Optional<Book> opt_book = bookRepository.getBookByIsbn(isbn);
        if(opt_book.isPresent()){
            BookDTO bookDTO = modelMapper.modelMapper().map(opt_book.get(),BookDTO.class);
            return
                    new ResponseEntity<>(bookDTO,HttpStatus.OK);

        }
        else throw new BookNotFoundException("book with isbn '"+isbn+"' not found");

    }

    public ResponseEntity<BookDTO> addBook(Book book,String token) throws IOException {
        bookRepository.save(book);
        LibraryApi.addBookInLibrary(book.getId(),token);
        BookDTO bookDTO = modelMapper.modelMapper().map(book,BookDTO.class);
        return new ResponseEntity<>(bookDTO,HttpStatus.OK);
    }

    public ResponseEntity<BookDTO> updateBook(Long id, Book book) throws BookNotFoundException {
        Optional<Book> opt_book = bookRepository.findById(id);
        if(opt_book.isPresent()){
            opt_book.get().setAuthor(book.getAuthor());
            opt_book.get().setName(book.getName());
            opt_book.get().setGenre(book.getGenre());
            opt_book.get().setIsbn(book.getIsbn());
            opt_book.get().setDescription(book.getDescription());
            bookRepository.save(opt_book.get());
            BookDTO bookDTO = modelMapper.modelMapper().map(opt_book.get(),BookDTO.class);

            return
                    new ResponseEntity<>(bookDTO,HttpStatus.OK);
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
