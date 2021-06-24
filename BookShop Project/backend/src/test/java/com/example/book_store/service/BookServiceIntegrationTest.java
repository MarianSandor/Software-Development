package com.example.book_store.service;

import com.example.book_store.TestCreationFactory;
import com.example.book_store.dto.BookDTO;
import com.example.book_store.model.Book;
import com.example.book_store.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookServiceIntegrationTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    @Test
    void findAll() {
        List<Book> items = TestCreationFactory.listOf(Book.class);
        System.out.println("ASDAS");
        bookRepository.saveAll(items);

        List<BookDTO> all = bookService.findAll();

        Assertions.assertEquals(items.size(), all.size());
    }
}
