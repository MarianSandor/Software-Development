package com.example.book_store.repository;

import com.example.book_store.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static com.example.book_store.repository.specifications.BookSpecifications.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {
    @Autowired
    private BookRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
    }

    @Test
    public void testMock() {
        Book bookSaved = repository.save(Book.builder()
                .title("whatever")
                .author("whatever")
                .genre("dasa")
                .price(32)
                .quantity(4)
                .build());

        assertNotNull(bookSaved);

        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(Book.builder().build());
        });
    }

    @Test
    public void testSearch() {
        Book book1 = repository.save(Book.builder()
                .title("A wild Book")
                .author("Me")
                .genre("Poetry")
                .price(32)
                .quantity(4)
                .build());

        Book book2 = repository.save(Book.builder()
                .title("Another wild Book")
                .author("You")
                .genre("SF")
                .price(32)
                .quantity(4)
                .build());

        Book book3 = repository.save(Book.builder()
                .title("This is not a book")
                .author("Anyone")
                .genre("Poetry")
                .price(32)
                .quantity(4)
                .build());

        String title = "%Book%";
        List<Book> books = repository.findAll(titleLike(title));

        assertEquals(2, books.size());

        String genre = "%Poet%";
        books = repository.findAll(titleLike(title).or(genreLike(genre)));

        assertEquals(3, books.size());

        String author = "%Yo%";
        books = repository.findAll(genreLike(genre).and(authorLike(author)));

        assertEquals(0, books.size());
    }
}