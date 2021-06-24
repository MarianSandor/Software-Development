package com.example.pcpartshop.repository.part;

import com.example.pcpartshop.model.User;
import com.example.pcpartshop.model.part.CPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CPURepository extends JpaRepository<CPU, Long>, JpaSpecificationExecutor<CPU> {
}
