package com.frog.demo.config;

import com.frog.demo.mapper.BookRepository;
import com.frog.demo.model.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Create Tables when spring starts.
 */
@Configuration
public class CommandConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {
            bookRepository.createBooksTable();
            bookRepository.createBookSeq();
        };
    }
}
