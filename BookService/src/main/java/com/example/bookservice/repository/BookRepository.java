package com.example.bookservice.repository;

import com.example.bookservice.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book,Long> {
    Optional<Book> getBookByIsbn(String isbn);

}
