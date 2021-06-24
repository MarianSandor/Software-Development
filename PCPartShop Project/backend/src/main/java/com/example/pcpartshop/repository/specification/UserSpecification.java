package com.example.pcpartshop.repository.specification;

import com.example.pcpartshop.model.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {

    public static Specification<User> usernameLike(String username) {
        return (root, query, cb) -> cb.like(root.get("username"), "%" + username + "%");
    }

    public static Specification<User> emailLike(String email) {
        return (root, query, cb) -> cb.like(root.get("email"), "%" + email + "%");
    }
}
