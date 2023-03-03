package com.interview.library;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    private final String BOOKS_ENDPOINT_URL = "http://localhost:8080/books";

    public List<Book> getBooks() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Book[]> response = restTemplate.getForEntity(BOOKS_ENDPOINT_URL, Book[].class);
        Book[] books = response.getBody();
        return books != null ? Arrays.asList(books) : new ArrayList<>();
    }
}
