package com.example.bookservice.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String isbn;
    private String name;
    private String genre;
    private String description;
    private String author;
}