package com.example.book_store;

import com.example.book_store.mapper.UserMapper;
import com.example.book_store.model.Book;
import com.example.book_store.model.ERole;
import com.example.book_store.model.Role;
import com.example.book_store.model.User;
import com.example.book_store.repository.BookRepository;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class BookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreApplication.class, args);
    }


}
