package com.example.pcpartshop.dto;

import com.example.pcpartshop.dto.part.minimal.MinimalPartDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigurationDto {

    private Long id;
    private String description;
    private MinimalPartDto cpu;
    private MinimalPartDto gpu;
    private MinimalPartDto motherboard;
    private MinimalPartDto memory;
    private MinimalPartDto storage;
    private MinimalPartDto psu;
    private MinimalPartDto pcCase;
    private String dateCreated;
    private double total;
}
