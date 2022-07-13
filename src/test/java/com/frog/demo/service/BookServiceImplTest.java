package com.frog.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.frog.demo.exception.ParamException;
import com.frog.demo.model.Book;
import com.frog.demo.util.BookUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Test
    void createBookWithInvalidValues() {
        //given
        Book book1 = new Book();
        book1.setBookName("bookName1")
                .setAuthor(null) // null value for test
                .setHouse("Taipei House")
                .setPrice(new BigDecimal(0)) //The price should be not less than 1
                .setPrint(1)
                .setPublishDate("2022");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        //when //then
        assertThrows(ParamException.class, () -> {
            bookService.createBooks(books);
        });
    }


    @Test
    void createAndQueryAndBooks() throws JsonProcessingException {
        //given
        Book book2 = new Book();
        book2.setBookName("bookName2")
                .setAuthor("Mike")
                .setHouse("Taipei House")
                .setPrice(new BigDecimal(50))
                .setPrint(1)
                .setPublishDate("2022");
        Book book3 = new Book();
        book3.setBookName("bookName3")
                .setAuthor("John")
                .setHouse("London House")
                .setPrice(new BigDecimal(60))
                .setPrint(1)
                .setPublishDate("2018");
        List<Book> testBooks = new ArrayList<>();
        testBooks.add(book2);
        testBooks.add(book3);
        bookService.createBooks(testBooks); // create books

        //when, It's fuzzy query by name search.
        List<Book> actualBooks = bookService.queryBooks(null, "bookName", null, null, null, null);
        // then validate result size and info
        assertEquals(testBooks.size(), actualBooks.size());

        // when query without any value
        List<Book> actualThisYearBooks = bookService.queryBooks(null, null, null, null, null, null);
        // then should only show the book's published this year
        assertEquals(1, actualThisYearBooks.size()); // only book2 is 2022
        assertEquals("2022", actualThisYearBooks.get(0).getPublishDate());

        bookService.deleteByBookIds(BookUtil.getBookIds(actualBooks)); //clear test data
    }

    @Test
    void modifyBookInfo() {
        //given
        Book book4 = new Book();
        book4.setBookName("bookName4")
                .setAuthor("Mike")
                .setHouse("Taipei House")
                .setPrice(new BigDecimal(50))
                .setPrint(1)
                .setPublishDate("2022");
        List<Book> testBooks = new ArrayList<>();
        testBooks.add(book4);
        bookService.createBooks(testBooks); //insert book4 to db
        Book modifiedBook = bookService.queryBooks(null, "bookName4", null, null, null, null).get(0);

        //when modify name, price
        modifiedBook.setBookName("bookName5")
                .setPrice(new BigDecimal(1000));
        List<Book> modbooks = new ArrayList<>();
        modbooks.add(modifiedBook);
        int successCount = bookService.updateBooks(modbooks);

        // then
        Book actualBook = bookService.queryBooks(null, "bookName5", null, null, null, null).get(0);
        assertEquals(1, successCount); //the update count should be 1
        assertEquals(modifiedBook.getBookName(), actualBook.getBookName());
        assertEquals(modifiedBook.getPrice().toBigInteger(), actualBook.getPrice().toBigInteger());

        bookService.deleteByBookIds(actualBook.getId().toString()); //clear test data
    }

    @Test
    void deleteBook() {
        //given
        Book book6 = new Book();
        book6.setBookName("bookName6")
                .setAuthor("Mike")
                .setHouse("Taipei House")
                .setPrice(new BigDecimal(50))
                .setPrint(1)
                .setPublishDate("2022");
        List<Book> testBooks = new ArrayList<>();
        testBooks.add(book6);
        bookService.createBooks(testBooks); //insert book6 to db
        Book actualBook = bookService.queryBooks(null, "bookName6", null, null, null, null).get(0);
        //when
        bookService.deleteByBookIds(actualBook.getId().toString());
        //then
        List<Book> actualBooks = bookService.queryBooks(null, "bookName6", null, null, null, null);
        assertEquals(0, actualBooks.size());
    }


}