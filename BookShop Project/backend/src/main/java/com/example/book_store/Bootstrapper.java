package com.example.book_store;

import com.example.book_store.dto.SignupRequest;
import com.example.book_store.model.Book;
import com.example.book_store.model.ERole;
import com.example.book_store.model.Role;
import com.example.book_store.repository.BookRepository;
import com.example.book_store.repository.RoleRepository;
import com.example.book_store.repository.UserRepository;
import com.example.book_store.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final BookRepository bookRepository;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            bookRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("marian@email.com")
                    .username("marian")
                    .password("123")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("marianC@email.com")
                    .username("marianC")
                    .password("321")
                    .roles(Set.of("CUSTOMER"))
                    .build());

            bookRepository.save(Book.builder()
                    .author("Marian")
                    .genre("SF")
                    .title("Viata mea")
                    .price(32.5)
                    .quantity(3)
                    .build());

            bookRepository.save(Book.builder()
                    .author("Marian")
                    .genre("History")
                    .title("The future")
                    .price(99.99)
                    .quantity(0)
                    .build());

            bookRepository.save(Book.builder()
                    .author("Marian al doilea")
                    .genre("History")
                    .title("Viata ta")
                    .price(100.0)
                    .quantity(0)
                    .build());
        }
    }
}