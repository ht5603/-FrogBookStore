package com.frog.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frog.demo.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Test
    public void creatBookWithNullValue() throws Exception {
        Book book = new Book();
        book.setBookName("book1")
                .setPrice(new BigDecimal(100))
                .setAuthor(null) // null value here is illegal
                .setHouse("Tokyo")
                .setPublishDate("2022");
        List<Book> books = new ArrayList<>();
        books.add(book);
        mvc.perform( MockMvcRequestBuilders
                        .post("/api/v1/books")
                        .content(mapper.writeValueAsString(books))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()); //should return 400 response
    }

}