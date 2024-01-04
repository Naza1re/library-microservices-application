package com.example.libraryservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "library")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Setter
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Long id;

    @Column(name = "book_id")
    private Long bookId;
    @Column(name = "took_date")
    private LocalDate tookDate;
    @Column(name = "return_date")
    private LocalDate return_date;
}
