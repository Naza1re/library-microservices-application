package com.example.libraryservice.service;

import com.example.libraryservice.BookApi.Book;
import com.example.libraryservice.BookApi.BookApi;
import com.example.libraryservice.dto.LibraryDTO;
import com.example.libraryservice.dto.ModelMapperConfig;
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
    private final ModelMapperConfig mapperConfig;
    @Autowired
    public LibraryService(LibraryRepository libraryRepository, ModelMapperConfig mapperConfig) {
        this.libraryRepository = libraryRepository;
        this.mapperConfig = mapperConfig;
    }



    public ResponseEntity<List<Book>> getAllFreeBooks(String token) throws LibraryNotFoundException, IOException {
        List<Book> listBook =  BookApi.getAllBooks(token);
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

    public HttpStatus addBookRecord(Long bookId) {
        Library library = new Library();
        library.setBookId(bookId);
        libraryRepository.save(library);
        return HttpStatus.OK;
    }

    public ResponseEntity<LibraryDTO> updateLibrary(Long id, Library library) throws LibraryNotFoundException {

        Optional<Library> opt_library = libraryRepository.findByBookId(id);
        if(opt_library.isPresent()){
            opt_library.get().setTookDate(library.getTookDate());
            opt_library.get().setReturn_date(library.getReturn_date());
            libraryRepository.save(opt_library.get());
            LibraryDTO libraryDTO = mapperConfig.modelMapper().map(opt_library.get(), LibraryDTO.class);
            return
                    new ResponseEntity<>(libraryDTO,HttpStatus.OK);
        }
        else
            throw new LibraryNotFoundException("library record with id '"+id+"' not found");

    }

    public ResponseEntity<List<LibraryDTO>> getAllLibrary(){
        List<Library> libraries = libraryRepository.findAll();
        List<LibraryDTO> libraryDTOS = libraries.stream()
                .map(book -> mapperConfig.modelMapper().map(book, LibraryDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(libraryDTOS,HttpStatus.OK);
    }

    public ResponseEntity<LibraryDTO> getLibraryRecordById(Long id) throws LibraryNotFoundException {
        Optional<Library> opt_library = libraryRepository.findById(id);
        if(opt_library.isPresent()){
            return new ResponseEntity<>(mapperConfig.modelMapper().map(opt_library.get(),LibraryDTO.class),HttpStatus.OK);
        }
        else
            throw new LibraryNotFoundException("Library with id '"+id+"' not found");

    }
}
