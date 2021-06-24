package com.example.pcpartshop.repository.part;

import com.example.pcpartshop.model.part.GPU;
import com.example.pcpartshop.model.part.Motherboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MotherboardRepository extends JpaRepository<Motherboard, Long>, JpaSpecificationExecutor<Motherboard> {
}
