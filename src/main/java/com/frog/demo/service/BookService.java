package com.frog.demo.service;

import com.frog.demo.model.Book;

import java.util.List;

public interface BookService {

    int createBooks(List<Book> books);

    List<Book> queryBooks(String bookIds, String bookName, String author, String house, String publishDtStart, String publishDtEnd);

    int updateBooks(List<Book> books);

    int deleteByBookIds(String bookIds);



}
