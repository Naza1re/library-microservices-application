package com.example.libraryservice.dto;

import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Getter
@Setter
public class LibraryDTO {
    private Long id;
    private Long bookId;
    private LocalDate tookDate;
    private LocalDate return_date;

}
