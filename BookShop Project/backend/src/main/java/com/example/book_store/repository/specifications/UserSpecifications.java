package com.example.book_store.repository.specifications;

import com.example.book_store.model.Book;
import com.example.book_store.model.ERole;
import com.example.book_store.model.Role;
import com.example.book_store.model.User;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;


public class UserSpecifications {

    public static Specification<User> usernameLike(String username) {
        return (Specification<User>) (root, query, cb) -> cb.like(root.get("username"), username);
    }

    public static Specification<User> emailLike(String email) {
        return (Specification<User>) (root, query, cb) -> cb.like(root.get("email"), email);
    }

    public static Specification<User> userWithRole(ERole r) {
        return (Specification<User>) (root, query, criteriaBuilder) -> {
            query.distinct(true);
            final Join<Object, Object> role = root.join("roles", JoinType.INNER);

            return criteriaBuilder.equal(role.get("name"), r);
        };
    }
}
