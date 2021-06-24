package com.example.book_store.repository;

import com.example.book_store.model.ERole;
import com.example.book_store.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole role);

}
