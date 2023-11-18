package com.example.libraryservice.service;

import com.example.libraryservice.BookApi.Book;
import com.example.libraryservice.BookApi.BookApi;
import com.example.libraryservice.exception.LibraryNotFoundException;
import com.example.libraryservice.model.Library;
import com.example.libraryservice.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }


    public ResponseEntity<List<Book>> getAllFreeBooks() throws LibraryNotFoundException, IOException {
        List<Book> listBook =  BookApi.getAllBooks();
        Optional<List<Library>> opt_library = libraryRepository.findByTookDateIsNull();

        if(opt_library.isPresent()){
            System.out.println(listBook);
            List<Book> matchingBooks = listBook.stream()
                    .filter(book -> opt_library.get().stream()
                            .anyMatch(record -> record.getBookId() == book.getId()))
                    .collect(Collectors.toList());

            return
                    new ResponseEntity<>(matchingBooks, HttpStatus.OK);
        }
        else
            throw new LibraryNotFoundException("there is no free book in library");
    }

    public HttpStatus deleteLibraryRecordAboutBook(Long id) throws LibraryNotFoundException {
        Optional<Library> opt_library = libraryRepository.findByBookId(id);
        if(opt_library.isPresent()){
            libraryRepository.delete(opt_library.get());
            return HttpStatus.OK;
        }
        else
            throw new LibraryNotFoundException("library record with bookId '"+id+"' not found");

    }
}
