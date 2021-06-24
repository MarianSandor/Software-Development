package com.example.pcpartshop.repository.part;

import com.example.pcpartshop.model.part.GPU;
import com.example.pcpartshop.model.part.PSU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PSURepository extends JpaRepository<PSU, Long>, JpaSpecificationExecutor<PSU> {
}
