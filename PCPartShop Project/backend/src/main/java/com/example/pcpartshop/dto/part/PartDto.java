package com.example.pcpartshop.dto.part;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class PartDto {
    protected Long id;
    protected String brand;
    protected String model;
    protected double price;
    protected int quantity;
}
