package com.example.pcpartshop.repository.part;

import com.example.pcpartshop.model.part.GPU;
import com.example.pcpartshop.model.part.PcCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PcCaseRepository extends JpaRepository<PcCase, Long>, JpaSpecificationExecutor<PcCase> {
}
