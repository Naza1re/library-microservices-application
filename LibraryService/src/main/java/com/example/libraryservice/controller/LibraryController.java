package com.example.libraryservice.controller;

import com.example.libraryservice.BookApi.Book;
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

    @GetMapping("/free")
    public ResponseEntity<List<Book>> getFreeBooks() throws LibraryNotFoundException, IOException {
        return libraryService.getAllFreeBooks();
    }
    @DeleteMapping("/{id}/delete")
    public HttpStatus deleteLibraryRecordAboutBook(@PathVariable Long id) throws LibraryNotFoundException {
        return libraryService.deleteLibraryRecordAboutBook(id);
    }
}
