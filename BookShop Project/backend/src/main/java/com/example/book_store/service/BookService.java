package com.example.book_store.service;

import com.example.book_store.dto.BookDTO;
import com.example.book_store.mapper.BookMapper;
import com.example.book_store.model.Book;
import com.example.book_store.repository.BookRepository;
import com.example.book_store.repository.specifications.BookSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.book_store.repository.specifications.BookSpecifications.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private Book findById(Long id) {
        return bookRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<BookDTO> search(String query) {
        String likeQuery = "%" + query + "%";

        return bookRepository.findAll(titleLike(likeQuery).or(authorLike(likeQuery)).or(genreLike(likeQuery))).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BookDTO create(BookDTO book) {
        return bookMapper.toDTO(bookRepository.save(
                bookMapper.fromDTO(book)
        ));
    }

    public BookDTO edit(Long id, BookDTO book) {
        Book currBook = findById(id);

        currBook.setTitle(book.getTitle());
        currBook.setAuthor(book.getAuthor());
        currBook.setGenre(book.getGenre());
        currBook.setPrice(book.getPrice());
        currBook.setQuantity(book.getQuantity());

        return bookMapper.toDTO(bookRepository.save(currBook));
    }

    public BookDTO updateQuantity(Long id, int quantity) {
        Book currBook = findById(id);
        int currQuantity = currBook.getQuantity();

        currBook.setQuantity(currQuantity - quantity);

        return bookMapper.toDTO(bookRepository.save(currBook));
    }

    public BookDTO get(Long id) {
        return bookMapper.toDTO(findById(id));
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
