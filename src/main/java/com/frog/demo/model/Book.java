package com.frog.demo.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public class Book {

    @Schema(description = "Book id, carried in automatically when create", example = "1")
    private Integer id;

    @Schema(description = "Book name", example = "bookName1")
    private String bookName;

    @Schema(description = "author", example = "Mike")
    private String author;

    @Schema(description = "price", example = "10")
    private BigDecimal price;

    @Schema(description = "publishing house", example = "Tokyo publishing house")
    private String house;

    @Schema(description = "publishing date", example = "2022")
    private String publishDate;

    @Schema(description = "print of book", example = "1")
    private Integer print;

    @Schema(description = "create time(yyyyMMddHHmmss), carried in automatically", example = "20220712090000")
    private String createDt;

    @Schema(description = "update time(yyyyMMddHHmmss), carried in automatically", example = "20220712090000")
    private String updateDt;

    public Integer getId() {
        return id;
    }

    public Book setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getBookName() {
        return bookName;
    }

    public Book setBookName(String bookName) {
        this.bookName = bookName;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getHouse() {
        return house;
    }

    public Book setHouse(String house) {
        this.house = house;
        return this;
    }

    public String getCreateDt() {
        return createDt;
    }

    public Book setCreateDt(String createDt) {
        this.createDt = createDt;
        return this;
    }

    public String getUpdateDt() {
        return updateDt;
    }

    public Book setUpdateDt(String updateDt) {
        this.updateDt = updateDt;
        return this;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public Book setPublishDate(String publishDate) {
        this.publishDate = publishDate;
        return this;
    }

    public Integer getPrint() {
        return print;
    }

    public Book setPrint(Integer print) {
        this.print = print;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Book setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
