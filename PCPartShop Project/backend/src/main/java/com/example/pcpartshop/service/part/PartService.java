package com.example.pcpartshop.service.part;

import com.example.pcpartshop.dto.part.PartDto;
import com.example.pcpartshop.model.part.Part;

public abstract class PartService {

    protected void setFromDto(Part part,PartDto partDto) {
        part.setBrand(partDto.getBrand());
        part.setModel(partDto.getModel());
        part.setPrice(partDto.getPrice());
        part.setQuantity(partDto.getQuantity());
    }
}
