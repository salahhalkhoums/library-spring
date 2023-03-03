package com.interview.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/search")
    public List<Book> search(@RequestParam("q") String searchTerm) {
        List<Book> books = bookService.getBooks();
        List<Book> matchingBooks = books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchTerm)
                        || book.getAuthor().toLowerCase().contains(searchTerm)
                        || book.getCategory().toLowerCase().contains(searchTerm))
                .sorted(Comparator.comparing(Book::getTitle))
                .collect(Collectors.toList());
        return matchingBooks;
    }

    // the following code block is for testing purposes, we create a "/books" endpoint
    static List<Book> books = new ArrayList<>();
    static {
        books.add(new Book(1L,"The Great Gatsby","F. Scott Fitzgerald","Fiction"));
        books.add(new Book(2L,"To Kill a Mockingbird","Harper Lee","Fiction"));
        books.add(new Book(3L,"1984","George Orwell","Science Fiction"));
    }
    @GetMapping("/books")
    public List<Book> getBooksTest(){
        return books;
    }
}
