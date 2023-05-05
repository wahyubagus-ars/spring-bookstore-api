package com.example.demo.service;

import com.example.demo.domain.dto.BookDto;
import org.springframework.http.ResponseEntity;

public interface BookService {

    ResponseEntity<Object> getBooksData();

    ResponseEntity<Object> getBookById(Long id);

    ResponseEntity<Object> addBookData(BookDto request);

    ResponseEntity<Object> editBookData(Long id, BookDto request);

    ResponseEntity<Object> deleteBookDataById(Long id);

}
