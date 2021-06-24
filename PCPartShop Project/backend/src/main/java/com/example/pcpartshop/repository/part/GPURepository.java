package com.example.pcpartshop.repository.part;

import com.example.pcpartshop.model.User;
import com.example.pcpartshop.model.part.GPU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GPURepository extends JpaRepository<GPU, Long>, JpaSpecificationExecutor<GPU> {
}
