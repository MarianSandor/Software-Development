package com.example.pcpartshop.repository;

import com.example.pcpartshop.model.User;
import com.example.pcpartshop.model.part.CPU;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import static com.example.pcpartshop.repository.specification.UserSpecification.emailLike;
import static com.example.pcpartshop.repository.specification.UserSpecification.usernameLike;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    public void beforeEach() {
        repository.deleteAll();
        roleRepository.deleteAll();
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