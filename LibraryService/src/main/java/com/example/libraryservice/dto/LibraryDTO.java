package com.example.libraryservice.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
public class LibraryDTO {
    private Long id;
    private Long bookId;
    private LocalDate tookDate;
    private LocalDate return_date;

}
