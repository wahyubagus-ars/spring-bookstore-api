package com.example.demo.controller;

import com.example.demo.domain.dto.BookDto;
import com.example.demo.service.BookService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("")
    public ResponseEntity<Object> getBooksData() {
        return bookService.getBooksData();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookById(@PathVariable(name = "id") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> addBookData(@RequestBody BookDto request) {
        return bookService.addBookData(request);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> editBookData(@PathVariable(name = "id") Long bookId, @RequestBody BookDto request) {
        return bookService.editBookData(bookId, request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable(name = "id") Long bookId) {
        return bookService.deleteBookDataById(bookId);
    }
}
