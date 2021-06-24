package com.example.pcpartshop.mapper.part;

import com.example.pcpartshop.dto.part.PSUDto;
import com.example.pcpartshop.dto.part.PcCaseDto;
import com.example.pcpartshop.model.part.PSU;
import com.example.pcpartshop.model.part.PcCase;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PSUMapper {
    PSUDto toDto(PSU psu);

    PSU fromDto(PSUDto psuDto);
}
