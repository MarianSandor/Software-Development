package com.example.pcpartshop.model.part;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@MappedSuperclass
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public abstract class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String brand;

    @Column(nullable = false)
    protected String model;

    @Column(nullable = false)
    protected double price;

    @Column(columnDefinition = "int default 0")
    protected int quantity;
}
