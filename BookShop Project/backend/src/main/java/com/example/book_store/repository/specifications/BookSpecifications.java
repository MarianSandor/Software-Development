package com.example.book_store.repository.specifications;

import com.example.book_store.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> titleLike(String title) {
        return (Specification<Book>) (root, query, cb) -> cb.like(root.get("title"), title);
    }

    public static Specification<Book> authorLike(String author) {
        return (Specification<Book>) (root, query, cb) -> cb.like(root.get("author"), author);
    }

    public static Specification<Book> genreLike(String title) {
        return (Specification<Book>) (root, query, cb) -> cb.like(root.get("genre"), title);
    }

    public static Specification<Book> outOfStock() {
        return (Specification<Book>) (root, query, cb) -> cb.lessThanOrEqualTo(root.get("quantity"), 0);
    }
}
