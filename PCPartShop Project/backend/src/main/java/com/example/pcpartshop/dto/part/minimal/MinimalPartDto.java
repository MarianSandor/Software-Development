package com.example.pcpartshop.dto.part.minimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MinimalPartDto {

    private Long id;
    private String brand;
    private String model;
}
