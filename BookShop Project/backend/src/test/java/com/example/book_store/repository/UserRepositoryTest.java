package com.example.book_store.repository;

import com.example.book_store.model.Book;
import com.example.book_store.model.User;
import com.example.book_store.repository.specifications.UserSpecifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static com.example.book_store.repository.specifications.BookSpecifications.titleLike;
import static com.example.book_store.repository.specifications.UserSpecifications.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
    }

    @Test
    public void testMock() {
        User userSaved = repository.save(User.builder()
                .username("asdasd")
                .email("dasdas@gmail.com")
                .password("aaaaa")
                .build());

        assertNotNull(userSaved);

        assertThrows(DataIntegrityViolationException.class, () -> {
            repository.save(User.builder().build());
        });
    }

    @Test
    public void testSearch() {
        User user1 = User.builder()
                .username("marian")
                .email("marian@gmail.com")
                .password("aaaaa")
                .build();

        User user2 = User.builder()
                .username("altMarian")
                .email("marian4322@gmail.com")
                .password("aaaaa")
                .build();

        User user3 = User.builder()
                .username("noname")
                .email("noname@yahoo.com")
                .password("aaaaa")
                .build();

        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        String username = "%arian%";
        List<User> users = repository.findAll(usernameLike(username));

        assertEquals(2, users.size());

        String email = "%@yahoo.com";
        users = repository.findAll(usernameLike(username).or(emailLike(email)));

        assertEquals(3, users.size());

    }
}