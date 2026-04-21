package br.com.gutendex.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.awt.print.Book;
import java.util.List;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @ManyToMany(mappedBy = "authors")
    private List<Book> books;
}
