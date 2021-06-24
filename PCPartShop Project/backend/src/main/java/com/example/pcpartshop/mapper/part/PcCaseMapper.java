package com.example.pcpartshop.mapper.part;

import com.example.pcpartshop.dto.part.MotherboardDto;
import com.example.pcpartshop.dto.part.PcCaseDto;
import com.example.pcpartshop.model.part.Motherboard;
import com.example.pcpartshop.model.part.PcCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PcCaseMapper {
    PcCaseDto toDto(PcCase pcCase);

    PcCase fromDto(PcCaseDto pcCaseDto);
}
