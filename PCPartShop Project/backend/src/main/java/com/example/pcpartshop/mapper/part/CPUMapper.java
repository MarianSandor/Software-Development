package com.example.pcpartshop.mapper.part;

import com.example.pcpartshop.dto.part.CPUDto;
import com.example.pcpartshop.model.part.CPU;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CPUMapper {

    CPUDto toDto(CPU cpu);

    CPU fromDto(CPUDto cpuDto);
}
