package com.example.pcpartshop.mapper.part;

import com.example.pcpartshop.dto.part.GPUDto;
import com.example.pcpartshop.model.part.GPU;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GPUMapper {

    GPUDto toDto(GPU gpu);

    GPU fromDto(GPUDto gpuDto);
}
