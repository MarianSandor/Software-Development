package com.example.pcpartshop.repository;

import com.example.pcpartshop.model.Configuration;
import com.example.pcpartshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    List<Configuration> findConfigurationByUser(User user);
}
