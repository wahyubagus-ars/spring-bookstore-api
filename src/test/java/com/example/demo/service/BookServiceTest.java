package com.example.demo.service;

import com.example.demo.TechnicalAssessmentApplication;
import com.example.demo.domain.dao.Book;
import com.example.demo.domain.dto.ApiResponse;
import com.example.demo.domain.dto.BookDto;
import com.example.demo.exception.AppException;
import com.example.demo.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.example.demo.constant.AppConstant.ResponseKey.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TechnicalAssessmentApplication.class)
public class BookServiceTest {

    @Autowired
    private BookService service;

    @MockBean
    private BookRepository bookRepository;

    @Test
    public void getBooksData_TestSuccess() {
        when(bookRepository.findAll())
                .thenReturn(List.of(Book.builder()
                        .id(1L)
                        .title("Test")
                        .author("Jk")
                        .price(BigDecimal.ONE)
                        .isbn("9108313-289131-341")
                        .build()));

        ResponseEntity<Object> responseEntity = service.getBooksData();
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        assertEquals(SUCCESS, apiResponse.getResponseKey());
    }

    @Test
    public void getBooksData_unknownError() {
        when(bookRepository.findAll())
                .thenThrow(new RuntimeException());

        Assertions.assertThrows(AppException.class, () -> service.getBooksData());
    }

    @Test
    public void getBookDataById_success() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.of(Book.builder()
                                .id(1L)
                                .title("Test")
                                .author("System")
                                .price(BigDecimal.ONE)
                                .isbn("9108313-289131-341")
                        .build()));

        ResponseEntity<Object> responseEntity = service.getBookById(1L);
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        assertEquals(SUCCESS, apiResponse.getResponseKey());
    }

    @Test
    public void getBookDataById_dataNotFound() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(AppException.class, () -> service.getBookById(1L));

    }

    @Test
    public void getBookDataById_unknownError() {
        when(bookRepository.findById(any()))
                .thenThrow(new RuntimeException());

        Assertions.assertThrows(AppException.class, () -> service.getBookById(1L));

    }

    @Test
    public void addBookData_success() {
        ResponseEntity<Object> responseEntity = service.addBookData(BookDto.builder()
                        .title("Test")
                        .author("System")
                        .price(BigDecimal.ONE)
                        .isbn("9108313-289131-341")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        assertEquals(SUCCESS, apiResponse.getResponseKey());
    }

    @Test
    public void addBookData_unknownError() {
        when(bookRepository.save(any()))
                .thenThrow(new RuntimeException());

        Assertions.assertThrows(AppException.class, () -> service.addBookData(BookDto.builder()
                        .title("Test")
                        .author("System")
                        .price(BigDecimal.ONE)
                        .isbn("9108313-289131-341")
                        .build()));

    }

    @Test
    public void editBookData_success() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.of(Book.builder()
                        .id(1L)
                        .title("Test")
                        .author("System")
                        .price(BigDecimal.ONE)
                        .isbn("9108313-289131-341")
                        .build()));

        ResponseEntity<Object> responseEntity = service.editBookData(1L, BookDto.builder()
                .title("Test")
                .author("System")
                .price(BigDecimal.ONE)
                .isbn("9108313-289131-341")
                .build());
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        assertEquals(SUCCESS, apiResponse.getResponseKey());
    }

    @Test
    public void editBookData_dataNotFound() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(AppException.class, () -> service.editBookData(1L, BookDto.builder()
                .title("Test")
                .author("System")
                .price(BigDecimal.ONE)
                .isbn("9108313-289131-341")
                .build()));
    }

    @Test
    public void editBookData_unknownError() {
        when(bookRepository.findById(any()))
                .thenThrow(new RuntimeException());

        Assertions.assertThrows(AppException.class, () -> service.editBookData(1L, BookDto.builder()
                .title("Test")
                .author("System")
                .price(BigDecimal.ONE)
                .isbn("9108313-289131-341")
                .build()));
    }

    @Test
    public void deteleBookData_success() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.of(Book.builder()
                        .id(1L)
                        .title("Test")
                        .author("System")
                        .price(BigDecimal.ONE)
                        .isbn("9108313-289131-341")
                        .build()));

        ResponseEntity<Object> responseEntity = service.deleteBookDataById(1L);
        ApiResponse apiResponse = (ApiResponse) responseEntity.getBody();
        assertEquals(SUCCESS, apiResponse.getResponseKey());
    }

    @Test
    public void deleteBookData_dataNotFound() {
        when(bookRepository.findById(any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(AppException.class, () -> service.deleteBookDataById(1L));
    }

    @Test
    public void deleteBookData_unknownError() {
        when(bookRepository.findById(any()))
                .thenThrow(new RuntimeException());

        Assertions.assertThrows(AppException.class, () -> service.deleteBookDataById(1L));
    }
}
