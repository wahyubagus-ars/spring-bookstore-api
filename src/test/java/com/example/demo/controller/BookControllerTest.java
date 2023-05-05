package com.example.demo.controller;

import com.example.demo.domain.dto.ApiResponse;
import com.example.demo.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static com.example.demo.constant.AppConstant.ResponseKey.SUCCESS;
import static com.example.demo.constant.AppConstant.ResponseMessage.SUCCESS_MSG;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = BookController.class)
@EnableWebMvc
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private BookService bookService;

    @BeforeEach
    public void setup() {
        //Init MockMvc Object and build
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getBooksData() throws Exception {
        when(bookService.getBooksData())
                .thenReturn(new ResponseEntity<>(ApiResponse.builder().responseKey(SUCCESS).responseMessage(SUCCESS_MSG).build(), HttpStatus.OK));
        mockMvc.perform(get("/books")
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getBookDataById() throws Exception {
        when(bookService.getBookById(any()))
                .thenReturn(new ResponseEntity<>(ApiResponse.builder().responseKey(SUCCESS).responseMessage(SUCCESS_MSG).build(), HttpStatus.OK));
        mockMvc.perform(get("/books/{id}", 1L)
                        .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void addBookData() throws Exception {
        when(bookService.addBookData(any()))
                .thenReturn(new ResponseEntity<>(ApiResponse.builder().responseKey(SUCCESS).responseMessage(SUCCESS_MSG).build(), HttpStatus.OK));
        mockMvc.perform(post("/books")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"title\": \"Talijiwo2\",\n" +
                                "    \"author\" : \"Sujiwo Tejo\",\n" +
                                "    \"price\": 90000,\n" +
                                "    \"isbn\": \"978-602-8519-93-9\" \n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void editBookData() throws Exception {
        when(bookService.editBookData(any(), any()))
                .thenReturn(new ResponseEntity<>(ApiResponse.builder().responseKey(SUCCESS).responseMessage(SUCCESS_MSG).build(), HttpStatus.OK));
        mockMvc.perform(put("/books/{id}", 1L)
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"title\": \"Talijiwo2\",\n" +
                                "    \"author\" : \"Sujiwo Tejo\",\n" +
                                "    \"price\": 90000,\n" +
                                "    \"isbn\": \"978-602-8519-93-9\" \n" +
                                "}"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteBookDataById() throws Exception {
        when(bookService.deleteBookDataById(any()))
                .thenReturn(new ResponseEntity<>(ApiResponse.builder().responseKey(SUCCESS).responseMessage(SUCCESS_MSG).build(), HttpStatus.OK));
        mockMvc.perform(delete("/books/{id}", 1L)
                        .characterEncoding("utf-8")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }
}
