package com.example.libraryservice.controller;

import com.example.libraryservice.BookApi.Book;
import com.example.libraryservice.dto.LibraryDTO;
import com.example.libraryservice.exception.LibraryNotFoundException;
import com.example.libraryservice.model.Library;
import com.example.libraryservice.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@RequestMapping("/library")
@RestController
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    //For BookApi
    @PostMapping("/add-book-record/{book_id}")
    public HttpStatus addBookToLibraryRecord(@PathVariable Long book_id){
        return libraryService.addBookRecord(book_id);
    }
    //For BookApi


    @GetMapping("/all-records")
    public ResponseEntity<List<LibraryDTO>> getAllLibrary() throws LibraryNotFoundException {
        return libraryService.getAllLibrary();
    }
    @GetMapping("/{id}")
    public ResponseEntity<LibraryDTO> getLibraryRecordById(@PathVariable Long id) throws LibraryNotFoundException {
        return libraryService.getLibraryRecordById(id);
    }
    @GetMapping("/free")
    public ResponseEntity<List<Book>> getFreeBooks(@RequestHeader("Authorization") String token) throws LibraryNotFoundException, IOException {
        return libraryService.getAllFreeBooks(token);
    }
    @DeleteMapping("/{id}/delete")
    public HttpStatus deleteLibraryRecordAboutBook(@PathVariable Long id) throws LibraryNotFoundException {
        return libraryService.deleteLibraryRecordAboutBook(id);
    }
    @PutMapping("/{id}/update")
    public ResponseEntity<LibraryDTO> updateLibrary(@RequestBody Library library, @PathVariable Long id) throws LibraryNotFoundException {
        return libraryService.updateLibrary(id,library);
    }

}
