package com.example.demo.service.impl;

import com.example.demo.domain.dao.Book;
import com.example.demo.domain.dto.BookDto;
import com.example.demo.exception.AppException;
import com.example.demo.repository.BookRepository;
import com.example.demo.service.BookService;
import com.example.demo.util.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.demo.constant.AppConstant.ResponseKey.*;
import static com.example.demo.constant.AppConstant.ResponseMessage.*;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<Object> getBooksData() {
        List<BookDto> responseData = new ArrayList<>();

        try {
            List<Book> bookList = bookRepository.findAll();

            bookList.forEach(item -> {
                responseData.add(BookDto.builder()
                        .id(item.getId())
                        .title(item.getTitle())
                        .author(item.getAuthor())
                        .price(item.getPrice())
                        .isbn(item.getIsbn())
                        .build()
                );
            });

            log.info("success to get books data");
            return ResponseUtils.buildResponse(SUCCESS, SUCCESS_MSG, responseData, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Happened error when get books data. Error: {}", e.getMessage());
            log.trace("Got error when get books data. ", e);
            throw new AppException(UNKNOWN_ERROR, UNKNOWN_ERROR_MSG);
        }
    }

    @Override
    public ResponseEntity<Object> getBookById(Long id) {
        try {
            Optional<Book> book = bookRepository.findById(id);

            if (book.isEmpty()) {
                log.debug("data book with id {} is not found", id);
                throw new AppException(DATA_NOT_FOUND, DATA_NOT_FOUND_MSG);
            }

            BookDto responseData = BookDto.builder()
                    .id(book.get().getId())
                    .title(book.get().getTitle())
                    .author(book.get().getAuthor())
                    .price(book.get().getPrice())
                    .isbn(book.get().getIsbn())
                    .build();

            log.info("success to get book data by id");
            return ResponseUtils.buildResponse(SUCCESS, SUCCESS_MSG, responseData, HttpStatus.OK);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            log.error("Happened error when get book data by id. Error: {}", e.getMessage());
            log.trace("Got error when get book data by id. ", e);
            throw new AppException(UNKNOWN_ERROR, UNKNOWN_ERROR_MSG);
        }
    }

    @Override
    public ResponseEntity<Object> addBookData(BookDto request) {
        try {
            Long generatedId = System.nanoTime();
            Book book = Book.builder()
                    .id(generatedId)
                    .title(request.getTitle())
                    .author(request.getAuthor())
                    .price(request.getPrice())
                    .isbn(request.getIsbn())
                    .build();

            bookRepository.save(book);

            request.setId(generatedId);

            log.info("success to add book data");
            return ResponseUtils.buildResponse(SUCCESS, SUCCESS_MSG, request, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Happened error when add book data. Error: {}", e.getMessage());
            log.trace("Got error when add book data. ", e);
            throw new AppException(UNKNOWN_ERROR, UNKNOWN_ERROR_MSG);
        }
    }

    @Override
    public ResponseEntity<Object> editBookData(Long id, BookDto request) {
        try {
            Optional<Book> book = bookRepository.findById(id);

            if (book.isEmpty()) {
                log.debug("data book with id {} is not found", id);
                throw new AppException(DATA_NOT_FOUND, DATA_NOT_FOUND_MSG);
            }

            book.get().setTitle(request.getTitle());
            book.get().setAuthor(request.getAuthor());
            book.get().setPrice(request.getPrice());
            book.get().setIsbn(request.getIsbn());

            bookRepository.save(book.get());

            log.info("success to edit book data");
            return ResponseUtils.buildResponse(SUCCESS, SUCCESS_MSG, request, HttpStatus.OK);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            log.error("Happened error when edit book data. Error: {}", e.getMessage());
            log.trace("Got error when edit book data. ", e);
            throw new AppException(UNKNOWN_ERROR, UNKNOWN_ERROR_MSG);
        }
    }

    @Override
    public ResponseEntity<Object> deleteBookDataById(Long id) {
        try {
            Optional<Book> book = bookRepository.findById(id);

            if (book.isEmpty()) {
                log.debug("data book with id {} is not found", id);
                throw new AppException(DATA_NOT_FOUND, DATA_NOT_FOUND_MSG);
            }

            bookRepository.deleteById(id);

            log.info("success to delete book data");
            return ResponseUtils.buildResponse(SUCCESS, SUCCESS_MSG, null, HttpStatus.OK);
        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            log.error("Happened error when delete book data. Error: {}", e.getMessage());
            log.trace("Got error when delete book data. ", e);
            throw new AppException(UNKNOWN_ERROR, UNKNOWN_ERROR_MSG);
        }
    }
}
