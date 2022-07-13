package com.frog.demo.controller;

import com.frog.demo.common.RestResource;
import com.frog.demo.model.Book;
import com.frog.demo.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="api/v1/books")
public class BookController extends BaseController{

    @Autowired
    BookService bookService;

    @Operation(summary = "Query books.",
               description = "You can query books by id, names or other parameters.")
    @GetMapping
    public ResponseEntity<RestResource<Book>> queryBooks(
            @Parameter(description = "book id, splited by comma.", example = "1,2,3")
            @RequestParam(required = false) String bookIds,
            @Parameter(description = "bookName. It's fuzzy search and case sensitive.", example = "Name1")
            @RequestParam(required = false) String booknames,
            @Parameter(description = "author", example = "Mike")
            @RequestParam(required = false) String author,
            @Parameter(description = "publishing house", example = "house1")
            @RequestParam(required = false) String house,
            @Parameter(description = "publishing start date", example = "2010")
            @RequestParam(required = false) String publishDtStart,
            @Parameter(description = "publishing start end", example = "2022")
            @RequestParam(required = false) String publishDtEnd) {
        try {
            List<Book> result = bookService.queryBooks(bookIds, booknames, author, house, publishDtStart, publishDtEnd);
            return success(result);
        } catch (RuntimeException e) {
            return fail(e);
        }
    }

    @Operation(summary = "Create books.",
               description = "Create books which are never created.")
    @PostMapping
    public ResponseEntity<RestResource<String>> createBooks(@RequestBody @Validated List<Book> books) {
        try {
            int count = bookService.createBooks(books);
            return success(count);
        } catch (RuntimeException e) {
            return fail(e);
        }
    }

    @Operation(summary = "update books.",
               description = "update books, need to provide book id.")
    @PutMapping
    @Validated
    public ResponseEntity<RestResource<String>> updateBooks(@Valid @RequestBody List<Book> books) {
        try {
            int count = bookService.updateBooks(books);
            return success(count);
        } catch (RuntimeException e) {
            return fail(e);
        }
    }

    @Operation(summary = "Delete books.",
               description = "Delete book by book id.")
    @DeleteMapping("/{bookIds}")
    public ResponseEntity<RestResource<String>> deleteBooks(
            @Parameter(description = "book id, splited by comma", example = "1,2,3") @PathVariable String bookIds) {
        try {
            int count = bookService.deleteByBookIds(bookIds);
            return success(count);
        } catch (RuntimeException e) {
            return  fail(e);
        }
    }
}
