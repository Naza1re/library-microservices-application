package com.example.libraryservice.BookApi;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Book {
    private Long id;
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String author;
}
