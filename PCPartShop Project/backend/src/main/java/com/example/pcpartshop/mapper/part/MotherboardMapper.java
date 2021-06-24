package com.example.pcpartshop.mapper.part;

import com.example.pcpartshop.dto.part.MemoryDto;
import com.example.pcpartshop.dto.part.MotherboardDto;
import com.example.pcpartshop.model.part.Memory;
import com.example.pcpartshop.model.part.Motherboard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MotherboardMapper {
    MotherboardDto toDto(Motherboard motherboard);

    Motherboard fromDto(MotherboardDto motherboardDto);
}
