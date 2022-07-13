package com.frog.demo.service;

import com.frog.demo.exception.ParamException;
import com.frog.demo.mapper.BookRepository;
import com.frog.demo.model.Book;
import com.frog.demo.util.BookUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService{

    Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookRepository bookRepository;

    @Override
    public int createBooks(List<Book> books) {
        if (books == null && books.isEmpty()) { //Checking if list is null.
            throw new ParamException("Book parameters is empty.");
        }
        try {
            this.validate(books); //basic validation
            int count = bookRepository.createBooks(books);
            return count;
        } catch (RuntimeException e) {
            logger.error("e:", e);
            throw e;
        }
    }

    @Override
    public List<Book> queryBooks(String bookIds, String bookName, String author, String house, String publishDtStart, String publishDtEnd) {
        List<Book> result = null;
        try {
            Integer[] bookIdArray = BookUtil.spiltStrToIntArray(bookIds);
            if (StringUtils.isNotBlank(publishDtStart) && StringUtils.isBlank(publishDtEnd)) {
                publishDtEnd = publishDtStart; //If endDate is empty, then equals to startDate.
            }
            if (bookIdArray == null
                    && StringUtils.isBlank(bookName)
                    && StringUtils.isBlank(author)
                    && StringUtils.isBlank(house)
                    && StringUtils.isBlank(publishDtStart)) {
                //Avoid querying a huge of data at one time, so default query data of this year when all parameters are empty.
                publishDtStart = String.valueOf(Year.now().getValue());
                publishDtEnd = String.valueOf(Year.now().getValue());
            }
            result = bookRepository.queryBooks(bookIdArray, bookName, author, house, publishDtStart, publishDtEnd);
            return result;
        } catch (RuntimeException e) {
            logger.error("e:", e);
            throw e;
        }
    }

    @Override
    public int updateBooks(List<Book> books) {
        if (books == null || books.isEmpty()) { // Checking if list is null.
            throw new ParamException("Book parameters is empty.");
        }
        try {
            this.validate(books); //basic validation
            Integer[] bookIds = books.stream() //Validate data. The book doesn't exist in db, can't be updated.
                    .map(Book::getId)
                    .collect(Collectors.toSet())
                    .stream().toArray(Integer[]::new); //Because it's possible that there are repeat books, so use set to get the count.
            List<Book> dbBooks = bookRepository.queryBooks(bookIds, null, null , null, null, null);
            if (bookIds.length != dbBooks.size()) {
                throw new ParamException("Book doesn't exist in db.");
            }
            int count = bookRepository.updateBooks(books);
            return count;
        } catch (RuntimeException e) {
            logger.error("e:", e);
            throw e;
        }
    }

    @Override
    public int deleteByBookIds(String bookIds) {
        //validate book parameters
        if (StringUtils.isBlank(bookIds)) {
            throw new ParamException("Book id is empty.");
        }
        try {
            Integer[] bookIdArray = BookUtil.spiltStrToIntArray(bookIds);
            int count = bookRepository.deleteBooks(bookIdArray);
            return count;
        } catch (RuntimeException e) {
            logger.error("e:", e);
            throw e;
        }
    }

    private void validate(List<Book> books) {
        if (books == null || books.isEmpty())
            return;
        StringBuilder errMsg = new StringBuilder();
        for (Book book : books) {
            //basic parameter validation
            if (StringUtils.isBlank(book.getBookName())) {
                errMsg.append("name can't be empty, ");
            }
            if (StringUtils.isBlank(book.getAuthor())) {
                errMsg.append("author can't be empty, ");
            }
            if (book.getPrice() == null || book.getPrice().compareTo(BigDecimal.ONE) < 0) {
                errMsg.append("price can't be null or < less than 1, ");
            }
            if (StringUtils.isBlank(book.getHouse())) {
                errMsg.append("publishing house can't be empty, ");
            }
            if (StringUtils.isBlank(book.getPublishDate())) {
                errMsg.append("publishing date can't be empty, ");
            }
            if (book.getPrint() == null || book.getPrint() < 1) {
                errMsg.append("print can't be null or < less than 1, ");
            }
        }

        if (errMsg.length() > 0) {
            throw new ParamException("Validation Error : " + errMsg);
        }
    }

}
