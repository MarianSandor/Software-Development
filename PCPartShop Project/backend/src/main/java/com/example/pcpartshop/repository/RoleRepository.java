package com.example.pcpartshop.repository;

import com.example.pcpartshop.model.ERole;
import com.example.pcpartshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole role);

}
