package com.frog.demo.mapper;

import com.frog.demo.model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookRepository {

    void createBooksTable();

    void createBookSeq();

    int createBooks(@Param("books") List<Book> books);

    List<Book> queryBooks(@Param("bookIds") Integer[] bookIds
            , @Param("bookName") String bookName
            , @Param("author") String author
            , @Param("house") String house
            , @Param("publishDtStart") String publishDtStart
            , @Param("publishDtEnd") String publishDtEnd);

    int updateBooks(@Param("books") List<Book> books);

    int deleteBooks(@Param("bookIds") Integer[] bookIds);

}
