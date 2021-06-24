package com.example.pcpartshop.mapper.part;

import com.example.pcpartshop.dto.part.GPUDto;
import com.example.pcpartshop.dto.part.MemoryDto;
import com.example.pcpartshop.model.part.GPU;
import com.example.pcpartshop.model.part.Memory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemoryMapper {
    MemoryDto toDto(Memory memory);

    Memory fromDto(MemoryDto memoryDto);
}
